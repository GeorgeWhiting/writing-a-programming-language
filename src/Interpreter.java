public class Interpreter{
    String text;
    Integer pos;
    Token currentToken;

    Interpreter(String text){
        this.text = text;
        this.pos = 0;
        this.currentToken = null;
    }

    public void error(){
        throw new java.lang.RuntimeException("Error parsing input");
    }

    public Token getNextToken(){
        String text = this.text;
        Token outputToken = new Token("placeholder", null);

        if(this.pos > text.length()-1){
            return new Token("EOF", null);
        }

        char currentChar = text.charAt(this.pos);

        if(Character.isDigit(currentChar)){
            Token token = new Token("INT", Character.getNumericValue(currentChar));
            this.pos ++;
            outputToken = token;
        }

        if(currentChar == '+'){
            Token token = new Token("PLUS", currentChar);
            this.pos ++;
            outputToken = token;
        }

        return outputToken;
    }

    public void eat(String tokenType){
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
        Integer result = (Integer) left.value + (Integer) right.value;
        return result;
    }
}
