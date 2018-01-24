public class Assertion {

    public static String assertTest(String test, Integer expect) {
        Lexer lexer = new Lexer(test);
        Interpreter testinterpreter = new Interpreter(lexer);
        Object result = testinterpreter.expr();
        return (result.equals(expect) ? "TEST PASSED" : "TEST FAILED: expected " + Integer.toString(expect) + " got " + String.valueOf(result));
    }

}
