import java.util.*;

public class TestRunner {

    private static void runTests(ArrayList<Test> array) {
        for(int i = 1; i < array.size() + 1; i++) {
            System.out.println("Test " + i );
            String toPrint = Assertion.assertTest(array.get(i-1).assertion, array.get(i-1).expected);
            System.out.println(toPrint);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("Lexer identifies tokens test");
        Lexer lexer = new Lexer("please thanks say var 3 + - * / ( ) ; == = .");
        ArrayList<String> tokenNames = new ArrayList();
        //Characters are primitive thus can not be stored in an
        //Object array list therefore we must get token type not value
        while(lexer.currentChar != '\0') {
            tokenNames.add(lexer.getNextToken().type);
        }
        ArrayList<String> expected = new ArrayList<String>(Arrays.asList("PLEASE", "THANKS", "SAY", "ID", "INT", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "LPAREN", "RPAREN", "SEMI", "EQUALITY", "ASSIGN", "DOT"));
        boolean result = tokenNames.equals(expected);
        System.out.println(result ? "TEST PASSED" : "TEST FAILED expected " + expected + " got " + tokenNames);

        System.out.println();

        System.out.println("Parser interprets the tokens and builds an appropriate AST");
        Lexer lexer = new Lexer
    }

}
