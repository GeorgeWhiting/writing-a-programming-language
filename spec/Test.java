import java.util.*;

public class Test {

    String assertion;
    Object expected;

    public Test(String assertion, Integer expected, ArrayList<Test> destination) {
        this.assertion = assertion;
        this.expected = expected;
        destination.add(this);
    }

    public Test(String assertion, String expected, ArrayList<Test> destination) {
        this.assertion = assertion;
        this.expected = expected;
        destination.add(this);
    }

}
