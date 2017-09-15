package jingl.wang.algorithm;

/**
 * Created by Wang Jinglu on 2017/9/15.
 */
public class test {
    public static void main(String[] args) {
        int[] data = getArray(10);
        print(data);
        HellSort.sort(data);
        print(data);

    }

    public static int[] getArray(int c) {
        int[] array = new int[c];
        for (int i = 0; i<c; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
        return array;
    }

    public static void print(int[] array) {
        System.out.println();
        for (int n : array) {
            System.out.print(n + " ");
        }
    }
}
