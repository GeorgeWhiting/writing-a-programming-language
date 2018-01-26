import java.util.HashMap;

public class Lexer {

    private String text;
    private Integer pos;
    private Token currentToken;
    public char currentChar;
    public HashMap<String, Token> reservedKeywords = new HashMap<String, Token>();

    Lexer(String text) {
        this.text = text;
        this.pos = 0;
        this.currentChar = this.text.charAt(this.pos);
        reservedKeywords.put("please", new Token("PLEASE", "PLEASE"));
        reservedKeywords.put("thanks", new Token("THANKS", "THANKS"));
        reservedKeywords.put("say", new Token("SAY", "SAY"));
    }

    private Token id() {
        String result = "";
        while((this.currentChar != '\0') && (Character.isLetterOrDigit(this.currentChar))){
            result += this.currentChar;
            this.advanceCounter();
        }
        if (reservedKeywords.get(result) != null){
            return reservedKeywords.get(result);
        } else {
            return new Token("ID", result);
        }
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

    private char peep(){
        Integer peep_pos = this.pos +1;
        if (peep_pos > this.text.length() -1) {
            return '\0';
        } else {
            return this.text.charAt(peep_pos);
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
            if (Character.isLetter(this.currentChar)) {
                return this.id();
            }

            if (this.currentChar == ';'){
                this.advanceCounter();
                Token token = new Token("SEMI", currentChar);
                return token;
            }

            if (this.currentChar == '=' && this.peep() == '='){
                this.advanceCounter();
                this.advanceCounter();
                return new Token("EQUALITY", "==");
            }

            if (this.currentChar == '='){
                this.advanceCounter();
                return new Token("ASSIGN", currentChar);
            }

            if (this.currentChar == '.'){
                this.advanceCounter();
                Token token = new Token("DOT", currentChar);
                return token;
            }

            else {
                this.error();
            }
        }
        return new Token("EOF", null);
    }
}
