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

    public Object expr(){
        this.currentToken = this.getNextToken();

        Token left = this.currentToken;
        this.eat("INT");

        Token op = this.currentToken;
        if(op.type.equals("PLUS")){
            this.eat("PLUS");
        } else {
            this.eat("MINUS");
        }

        Token right = this.currentToken;
        this.eat("INT");

        if(op.type.equals("PLUS")){
            return simpleAddition(left, right);
        } else {
            return simpleSubtraction(left, right);
        }
    }

    private Integer simpleAddition(Token inputOne, Token inputTwo) {
        Integer result = (Integer) inputOne.value + (Integer) inputTwo.value;
        return result;
    }

    private Integer simpleSubtraction(Token inputOne, Token inputTwo) {
        Integer result = (Integer) inputOne.value - (Integer) inputTwo.value;
        return result;
    }

}
