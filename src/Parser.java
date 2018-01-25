import java.util.ArrayList;
public class Parser {

        public Lexer lexer;
        public Token currentToken;

    public Parser(Lexer lexer){
        this.lexer = lexer;
        this.currentToken = this.lexer.getNextToken();
    }

    private void error(){
        throw new java.lang.RuntimeException("Invalid syntax");
    }

    private void eat(String tokenType){
        if(this.currentToken.type.equals(tokenType)){
            this.currentToken = this.lexer.getNextToken();
        } else {
            this.error();
        }
    }

    private ASTree factor() {
        Token token = this.currentToken;
        ASTree node = null;

        if (token.type.equals("PLUS")){
            this.eat("PLUS");
            node = new UnaryOp(token, this.factor());
        } else if (token.type.equals("MINUS")){
            this.eat("MINUS");
            node = new UnaryOp(token, this.factor());
        } else if (token.type.equals("INT")) {
            this.eat("INT");
            node = new Num(token);
        } else if (token.type.equals("LPAREN")) {
            this.eat("LPAREN");
            node = this.expr();
            this.eat("RPAREN");
        } else {
            node = this.variable();
        }
        return node;
    }

    private ASTree term(){


        ASTree node = this.factor();

        while(this.currentToken.type.equals("MULTIPLY") || this.currentToken.type.equals("DIVIDE")){
            Token token = this.currentToken;
            if (token.type.equals("MULTIPLY")) {
                this.eat("MULTIPLY");
            } else if(token.type.equals("DIVIDE")) {
                this.eat("DIVIDE");
            }

            node = new BinOp(node, token, this.factor());
        }

        return node;
    }

    public ASTree expr(){


        ASTree node = this.term();

        while(this.currentToken.type.equals("PLUS") || this.currentToken.type.equals("MINUS")){
            Token token = this.currentToken;
            if(token.type.equals("PLUS")) {
                this.eat("PLUS");
            } else if(token.type.equals("MINUS")) {
                this.eat("MINUS");
            }

            node = new BinOp(node, token, this.term());
        }

        return node;
    }

    public ASTree parse(){
        ASTree node = this.program();
        if (!this.currentToken.type.equals("EOF")){
            this.error();
        }
        return node;
    }

    public ASTree program(){
        ASTree node = this.compoundStatement();
        this.eat("DOT");
        return node;
    }

    public ASTree compoundStatement(){
        ArrayList nodes;
        this.eat("PLEASE");
        nodes = this.statementList();
        System.out.println("mark 2");
        this.eat("THANKS");

        Compound root = new Compound();
        for(int i = 0; i < nodes.size(); i++ ){
            root.children.add(nodes.get(i));
        }

        return root;
    }

    public ArrayList statementList(){
        ASTree node = this.statement();

        ArrayList results = new ArrayList();
        results.add(node);

        while(this.currentToken.type.equals("SEMI")){
            this.eat("SEMI");
            results.add(this.statement());
        }

        if(this.currentToken.type.equals("ID")){
            this.error();
        }

        return results;
    }

    public ASTree statement(){
        ASTree node;
        if(this.currentToken.type.equals("PLEASE")){
            node = this.compoundStatement();
        } else if (this.currentToken.type.equals("ID")){
            node = this.assignmentStatement();
        } else {
            node = this.empty();
        }

        return node;
    }

    public ASTree assignmentStatement(){
        Var left;
        ASTree right;
        Token token;
        ASTree node;

        left = this.variable();
        token = this.currentToken;
        this.eat("ASSIGN");
        right = this.expr();
        node = new Assign(left, token, right);

        return node;
    }

    public Var variable(){
        Var node = new Var(this.currentToken);
        this.eat("ID");

        return node;
    }

    public ASTree empty(){
        return new NoOp();
    }

}
