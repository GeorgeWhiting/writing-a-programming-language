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
        ArrayList<Test> testBasicMath = new ArrayList<Test>();
        ArrayList<Test> testToken = new ArrayList<Test>();
        Assertion asserter = new Assertion();
        new Test("1+1", 2, testBasicMath);
        new Test("3 + 3", 6, testBasicMath);
        new Test("333 + 333", 666, testBasicMath);
        new Test("1    +   1", 2, testBasicMath);
        new Test("1111 + 1111", 2222, testBasicMath);
        new Test("3 - 1", 2, testBasicMath);
        new Test("3*3", 9, testBasicMath);
        new Test("    6 * 12", 72, testBasicMath);
        new Test("1 + 2 + 3", 6, testBasicMath);
        new Test("1 + 2 * 2", 5, testBasicMath);
        new Test("3/3", 1, testBasicMath);
        new Test("1+(3*3)", 10, testBasicMath);
        new Test("1+((3*3)+4)", 14, testBasicMath);
        new Test("1+((3/3)*4)", 5, testBasicMath);
        new Test("(1+((3/3)*4))+3", 8, testBasicMath);
        runTests(testBasicMath);
        new Test("1 + 5", "1", testToken);
        new Test("713 + 1230", "713", testToken);
        runTests(testToken);
    }

}
