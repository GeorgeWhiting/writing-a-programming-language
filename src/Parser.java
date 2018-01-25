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
        return this.expr();
    }




}
