import java.util.*;


public class Lexer {

    private String text;
    private Integer pos;
    private Token currentToken;
    private char currentChar;
    public HashMap<String, Token> RESERVED_KEYWORDS = new HashMap<String, Token>();

    RESERVED_KEYWORDS.put("PLEASE", new Token("PLEASE", "PLEASE"));
    RESERVED_KEYWORDS.put("THANKS", new Token("THANKS", "THANKS"));

    Lexer(String text) {
        this.text = text;
        this.pos = 0;
        this.currentChar = this.text.charAt(this.pos);

    }

    private Token _id() {
        String result = "";
        while((this.currentChar != '\0') && (Character.isLetterOrDigit(this.currentChar))){
            result += this.currentChar;
            this.advanceCounter();
        }
        return RESERVED_KEYWORDS.get(result);
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

    private char peek(){
        Integer peek_pos = this.pos +1;
        if (peek_pos > this.text.length() -1) {
            return '\0';
        } else {
            return this.text.charAt(peek_pos);
        }
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

            // checks for keywords
            if (Character.isLetterOrDigit(this.currentChar)) {
                return this._id();
            }

            if (this.currentChar == ';'){
                this.advanceCounter();
                Token token = new Token("SEMI", currentChar);
                return token;
            }

            else {
                this.error();
            }
        }
        return new Token("EOF", null);
    }
}
