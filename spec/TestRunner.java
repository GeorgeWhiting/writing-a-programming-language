import java.util.*;

public class TestRunner {

    private static void runTests(ArrayList<Test> array) {
        for(int i = 1; i < array.size() + 1; i++) {
            System.out.println("Test " + i );
            String toPrint = Assertion.assertTest(array.get(i-1).assertion, array.get(i-1).expected);
            System.out.println(toPrint);
        }
    }

    public static void main(String[] args) {
        ArrayList<Test> testArray = new ArrayList<Test>();
        Assertion asserter = new Assertion();
        Test test1 = new Test("1+1", 2);
        testArray.add(test1);
        Test test2 = new Test("3+3", 7);
        testArray.add(test2);
        runTests(testArray);
    }

}
