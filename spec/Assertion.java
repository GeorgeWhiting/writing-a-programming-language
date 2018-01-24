public class Assertion {

    public static String assertTest(String test, Object expect) {
        if(expect instanceof Integer) {

            Lexer lexer = new Lexer(test);
            Interpreter testInterpreter = new Interpreter(lexer);
            Object result = testInterpreter.expr();
            return (result.equals(expect) ? "TEST PASSED" : "TEST FAILED: expected " + expect.toString() + " got " + String.valueOf(result));

        } else if(expect instanceof String) {

            Lexer lexer = new Lexer(test);
            Object result = String.valueOf(lexer.getNextToken().value);
            return (result.equals(expect) ? "TEST PASSED" : "TEST FAILED: expected " + expect + " got " + String.valueOf(result));

        } else {

            return "Error";

        }

    }
}
