public class Lexer {

    private String text;
    private Integer pos;
    private Token currentToken;
    private char currentChar;

    Lexer(String text) {
        this.text = text;
        this.pos = 0;
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

    public Token getNextToken(){

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
            if (this.currentChar == '/') {
                this.advanceCounter();
                Token token = new Token("DIVIDE", currentChar);
                return token;
            }
            if (this.currentChar == '(') {
                this.advanceCounter();
                Token token = new Token("LPAREN", currentChar);
                return token;
            }
            if (this.currentChar == ')') {
                this.advanceCounter();
                Token token = new Token("RPAREN", currentChar);
                return token;
            }
            else {
                this.error();
            }
        }
        return new Token("EOF", null);
    }
}
