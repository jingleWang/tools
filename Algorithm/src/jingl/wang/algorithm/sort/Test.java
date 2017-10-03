package jingl.wang.algorithm.sort;

/**
 * Created by Wang Jinglu on 2017/9/15.
 */
public class Test {
    public static void main(String[] args) {
//        int[] data = getArray(5);
        int[] data = {92,87,12,47,62};
        print(data);
        QuickSort.sort(data);
        print(data);

    }

    public static int[] getArray(int c) {
        int[] array = new int[c];
        for (int i = 0; i<c; i++) {
            array[i] = (int) (Math.random() * 100);
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
