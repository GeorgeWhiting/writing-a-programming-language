public class Assertion {

    public static String assertTest(String test, Integer expect) {
        Interpreter testinterpreter = new Interpreter(test);
        Object result = testinterpreter.expr();
        return (result.equals(expect) ? "TEST PASSED" : "TEST FAILED: expected " + Integer.toString(expect) + " got " + String.valueOf(result));
    }

}
