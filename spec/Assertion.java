public class Assertion {

    public static String assertTest(String test, Integer expect) {
        Interpreter testinterpreter = new Interpreter(test);
        Integer result = testinterpreter.expr();
        return (result == expect ? "TEST PASSED" : "TEST FAILED: expected " + Integer.toString(expect) + " got " + Integer.toString(result));
    }

}
