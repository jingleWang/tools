package jingl.wang.algorithm.sort;

/**
 * Created by Wang Jinglu on 2017/9/15.
 */
public class BubbleSort extends Sort{
    public static int[] sort(int[] array) {
        for (int i = 0 ; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > 0; j--) {
                if (less(array[j-1], array[j])) {
                    exch(array, j-1, j);
                }
            }
        }
        return array;
    }
}
