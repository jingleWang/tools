import java.util.Scanner;

/**
 * Created by Ben on 21/09/2017.
 */
public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] params = sc.nextLine().split(" ");
        int find = Integer.valueOf(params[0]);
        int r = Integer.valueOf(params[1]);
        int c = Integer.valueOf(params[2]);
        int _r = -1;
        int _c = -1;

        boolean isFind = false;
        for (int i = 0 ;i < r; i++) {
            String[] datas = sc.nextLine().split(" ");
            if (isFind) continue;
            int j = 0;
            for (String num : datas) {
                if (Integer.valueOf(num) == find) {
                    _r = i;
                    _c = j;
                    isFind = true;
                    break;
                }
            }
        }
        System.out.println(_r + " " + _c);
    }
}
