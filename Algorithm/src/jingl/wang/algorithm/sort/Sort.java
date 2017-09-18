package jingl.wang.algorithm.sort;

/**
 * Created by Wang Jinglu on 2017/9/15.
 */
public abstract class Sort {
    protected static void exch(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    protected static boolean less(int a, int b) {
        return a<b;
    }
}
