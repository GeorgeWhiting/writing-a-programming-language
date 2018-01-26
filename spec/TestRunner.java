import java.lang.reflect.Array;
import java.util.*;

public class TestRunner {

    public static void main(String[] args) {
        System.out.println("Lexer identifies tokens test");
        Lexer lexer = new Lexer("please thanks say var 3 + - * / ( ) ; == = .");
        ArrayList<String> tokenNames = new ArrayList();
        //Characters are primitive thus can not be stored in an
        //Object array list therefore we must get token type not value
        while(lexer.currentChar != '\0') {
            tokenNames.add(lexer.getNextToken().type);
        }
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("PLEASE", "THANKS", "SAY", "ID", "INT", "PLUS", "MINUS", "MULTIPLY", "DIVIDE", "LPAREN", "RPAREN", "SEMI", "EQUALITY", "ASSIGN", "DOT"));
        test(expected, tokenNames);

        System.out.println("-----------------------------------------------------------");

        System.out.println("Parser interprets the tokens and builds an appropriate AST");
        Lexer lexer2 = new Lexer("please a = 1 + 1; say a; please thanks thanks.");
        Parser parser2 = new Parser(lexer2);
        Compound compound = (Compound) parser2.parse();
        ArrayList<ASTree> children = compound.children;
        ArrayList<String> stringNamesOfChildren = new ArrayList<>();
        for(int i = 0; i < children.size(); i++){
            stringNamesOfChildren.add(children.get(i).getClass().getSimpleName());
        }
        ArrayList<String> expected2 = new ArrayList<>(Arrays.asList("Assign", "Sayer", "Compound"));
        test(expected2, stringNamesOfChildren);

        System.out.println("-----------------------------------------------------------");

        System.out.println("Interpreter assigns correct values from the abstract syntax tree");
        Lexer lexer3 = new Lexer("please a = 1 + 1; b = 3 * (1 + 2); c = a * b; thanks.");
        Parser parser3 = new Parser(lexer3);
        Interpreter interpreter3 = new Interpreter(parser3);
        interpreter3.interpret();
        HashMap<Object, Object> symbolTable = interpreter3.symbolTable;

        ArrayList<Object> expected3 = new ArrayList<>(Arrays.asList(2, 9, 18));
        ArrayList<Object> symbolTableValues = new ArrayList<>();

        for(Object value : symbolTable.values()){
            symbolTableValues.add(value);
        }
        test(expected3, symbolTableValues);
    }

    public static void test(Object expected, Object outcome) {
        boolean result = outcome.equals(expected);
        System.out.println(result ? "TEST PASSED" : "TEST FAILED expected " + expected + " got " + outcome);
    }

}
