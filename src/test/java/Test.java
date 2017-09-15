import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ben on 2017/8/4.
 */
public class Test {
    String test;
    public static void main(String[] args) {
        List<Test> test = new ArrayList<Test>();

        Test t = new Test();
        t.test = "1";
        test.add(t);

        Test t1 = test.get(0);
        t1.test = "2";

        System.out.println(test);

        HashMap map = new HashMap();

        Node node;
        node = new Node();
        System.out.println(node.hashCode());
        node = new Node();
        System.out.println(node.hashCode());
        node = new Node();
        System.out.println(node.hashCode());
        node = new Node();
        System.out.println(node.hashCode());
    }

    @Override
    public String toString() {
        return "Test{" +
                "test='" + test + '\'' +
                '}';
    }
}
