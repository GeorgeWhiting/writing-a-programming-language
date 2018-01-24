public class Interpreter{
    private Integer pos;
    private Token currentToken;
    private char currentChar;
    private Lexer lexer;


    Interpreter(Lexer lexer){
        this.lexer = lexer;
        this.currentToken = null;
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

    private Object term() {
        Token token = this.currentToken;
        this.eat("INT");
        return token.value;
    }

    public Object expr(){
        this.currentToken = this.lexer.getNextToken();

        Object result = this.term();

        while(this.currentToken.type.equals("PLUS") || this.currentToken.type.equals("MINUS") || this.currentToken.type.equals("MULTIPLY")){
            Token token = this.currentToken;
            if(token.type.equals("PLUS")) {
                this.eat("PLUS");
                result = (Integer) result + (Integer) this.term();
            } else if(token.type.equals("MINUS")) {
                this.eat("MINUS");
                result = (Integer) result - (Integer) this.term();
            } else if(token.type.equals("MULTIPLY")) {
                this.eat("MULTIPLY");
                result = (Integer) result * (Integer) this.term();
            }
        }

        return result;
    }

}


