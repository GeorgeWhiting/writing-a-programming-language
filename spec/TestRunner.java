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
        ArrayList<Test> testArray = new ArrayList<Test>();
        Assertion asserter = new Assertion();
        new Test("1+1", 2, testArray);
        new Test("3 + 3", 6, testArray);
        new Test("333 + 333", 666, testArray);
        new Test("1    +   1", 2, testArray);
        new Test("1 + 1  ", 2, testArray);
        new Test("1111 + 1111", 2222, testArray);
        new Test("3 - 1", 2, testArray);
        new Test("3*3", 9, testArray);
        new Test("    6 * 12", 72, testArray);
        runTests(testArray);
    }

}
