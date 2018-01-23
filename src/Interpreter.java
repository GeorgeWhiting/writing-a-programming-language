public class Interpreter{
    private String text;
    private Integer pos;
    private Token currentToken;

    Interpreter(String text){
        this.text = text;
        this.pos = 0;
        this.currentToken = null;
    }

    private void error(){
        throw new java.lang.RuntimeException("Error parsing input");
    }

    private Token getNextToken(){

        // want to remove and export this gaurd into a new function
        if(this.pos > text.length()-1){
            return new Token("EOF", null);
        }

        String text = this.text;
        Token outputToken = new Token("placeholder", null);
        char currentChar = text.charAt(this.pos);

        if(Character.isDigit(currentChar)){
            Token token = new Token("INT", Character.getNumericValue(currentChar));
            this.pos ++;
            outputToken = token;
        } else if (currentChar == '+'){
            Token token = new Token("PLUS", currentChar);
            this.pos ++;
            outputToken = token;
        }

        return outputToken;
    }


    private void eat(String tokenType){
        if(this.currentToken.type == tokenType){
            this.currentToken = this.getNextToken();
        } else {
            this.error();
        }
    }

    public Integer expr(){
        this.currentToken = this.getNextToken();

        Token left = this.currentToken;
        this.eat("INT");

        Token op = this.currentToken;
        this.eat("PLUS");

        Token right = this.currentToken;
        this.eat("INT");

        return simpleAddition(left, right);
    }

    private Integer simpleAddition(Token inputOne, Token inputTwo) {
        Integer result = (Integer) inputOne.value + (Integer) inputTwo.value;
        return result;
    }

}
