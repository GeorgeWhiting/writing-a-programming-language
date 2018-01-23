public class Interpreter{
    private String text;
    private Integer pos;
    private Token currentToken;
    private char currentChar;

    Interpreter(String text){
        this.text = text;
        this.pos = 0;
        this.currentToken = null;
        this.currentChar = this.text.charAt(this.pos);
    }

    private void error(){
        throw new java.lang.RuntimeException("Error parsing input");
    }

    private void advanceCounter(){
        this.pos ++;
        if(this.pos > text.length()-1){
          this.currentChar = '\0';
        } else {
            this.currentChar = this.text.charAt(this.pos);
        }
    }

    private void whitespaceSkipper(){
        while(this.currentChar != '\0' && Character.isWhitespace(this.currentChar)){
            this.advanceCounter();
        }
    }

    private Integer integer(){
        String integerInProgress = "";
        while(Character.isDigit(this.currentChar)){
            integerInProgress += this.currentChar;
            this.advanceCounter();
        }
        return Integer.parseInt(integerInProgress);
    }

    private Token getNextToken(){

        while(this.currentChar != '\0'){

            if(Character.isWhitespace(this.currentChar)){
                this.whitespaceSkipper();
                continue;
            }
            if(Character.isDigit(this.currentChar)){
                Token token = new Token("INT", this.integer());
                return token;
            }
            if(this.currentChar == '+'){
                this.advanceCounter();
                Token token = new Token("PLUS", currentChar);
                return token;
            }
            if(this.currentChar == '-'){
                this.advanceCounter();
                Token token = new Token("MINUS", currentChar);
                return token;
            }
            if (this.currentChar == '*') {
                this.advanceCounter();
                Token token = new Token("MULTIPLY", currentChar);
                return token;
            }
            else {
                this.error();
            }
        }
        return new Token("EOF", null);
    }


    private void eat(String tokenType){
        if(this.currentToken.type.equals(tokenType)){
            this.currentToken = this.getNextToken();
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
        this.currentToken = this.getNextToken();

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


