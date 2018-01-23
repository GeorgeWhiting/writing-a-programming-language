import java.util.*;

public class Test {

    String assertion;
    Integer expected;

    public Test(String assertion, Integer expected, ArrayList<Test> destination) {
        this.assertion = assertion;
        this.expected = expected;
        destination.add(this);
    }

}
