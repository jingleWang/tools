package jingl.wang.algorithm;

/**
 * Created by Wang Jinglu on 2017/9/15.
 */
public class InsertionSort extends Sort {
    public static int[] sort(int[] array) {
        for (int i = 1 ; i < array.length; i++) {
            for (int j = i; j>0; j--) {
                if (less(array[j - 1], array[j])) {
                    exch(array, j - 1,j);
                }
            }
        }
        return array;
    }
}
