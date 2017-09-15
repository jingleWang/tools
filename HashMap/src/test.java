import jingl.wang.tools.HashMap;

/**
 * Created by Wang Jinglu on 2017/8/30.
 */
public class test {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "aa");
        map.put("b", "bb");
        map.put("c", "cc");

        System.out.println(map.get("a"));
        System.out.println(map.get("b"));
        System.out.println(map.get("c"));
    }
}
