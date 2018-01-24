public class Interpreter{
    private Integer pos;
    private char currentChar;
    private Parser parser;


    Interpreter(Parser parser){
        this.parser = parser;
    }

    private void error(){
        throw new java.lang.RuntimeException("Invalid syntax");
    }


}


