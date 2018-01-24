public class Interpreter{
    private Integer pos;
    private Token currentToken;
    private char currentChar;
    private Lexer lexer;


    Interpreter(Lexer lexer){
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

    private Object factor() {
        Object result = null;
        Token token = this.currentToken;
        if (token.type == "INT") {
            this.eat("INT");
            result = token.value;
        } else if (token.type == "LPAREN") {
            this.eat("LPAREN");
            result = this.expr();
            this.eat("RPAREN");
        }
        return result;
    }

    public Object expr(){


        Object result = this.term();

        while(this.currentToken.type.equals("PLUS") || this.currentToken.type.equals("MINUS")){
            Token token = this.currentToken;
            if(token.type.equals("PLUS")) {
                this.eat("PLUS");
                result = (Integer) result + (Integer) this.term();
            } else if(token.type.equals("MINUS")) {
                this.eat("MINUS");
                result = (Integer) result - (Integer) this.term();
            }
        }

        return result;
    }

    public Object term(){


        Object result = this.factor();

        while(this.currentToken.type.equals("MULTIPLY") || this.currentToken.type.equals("DIVIDE")){
            Token token = this.currentToken;
            if (token.type.equals("MULTIPLY")) {
                this.eat("MULTIPLY");
                result = (Integer) result * (Integer) this.factor();
            } else if(token.type.equals("DIVIDE")) {
                this.eat("DIVIDE");
                result = (Integer) result / (Integer) this.factor();
            }
        }

        return result;
    }

}


