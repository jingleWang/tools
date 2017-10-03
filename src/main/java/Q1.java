import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by Ben on 23/09/2017.
 */
public class Q1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String[] in = sc.nextLine().split(" ");
            int year = Integer.valueOf(in[0]);
            int month = Integer.valueOf(in[1]);
            int day = Integer.valueOf(in[2]);
            Calendar calendar = Calendar.getInstance();
            calendar.set(year,month - 1,day);
            System.out.println(calendar.get(Calendar.DAY_OF_YEAR));
        }
    }
}
