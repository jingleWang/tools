import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Ben on 21/09/2017.
 */
public class P1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        String[] strs = line.split(" ");

        for (int i = strs.length - 1; i>=0; i--) {
            if (strs[i].equals("")) continue;
            int count = 0;
            boolean zm = isZM(strs[i].charAt(0));
            for (int j = 0; j< strs[i].length();j++) {
                char c = strs[i].charAt(j);

                if (zm && !isZM(c)) {
                    break;
                } else {
                    count++;
                }
            }
            if (count>0) {
                String pre = strs[i].substring(0,count);
                String post = strs[i].substring(count);
                strs[i] = post + pre;
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = strs.length - 1; i>=0; i--) {
            if (first) {
                first = false;
            } else {
                sb.append(" ");
            }
            if (strs[i].equals(""))
                sb.append(" ");
            else
                sb.append(strs[i]);
        }
        System.out.println(sb.toString());

    }

    public static boolean isZM(char c) {
        return (c>'a'& c<'z') || (c>'A'&&c<'Z');
    }
}
