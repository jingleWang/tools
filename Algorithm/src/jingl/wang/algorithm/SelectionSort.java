package jingl.wang.algorithm;

/**
 * Created by Wang Jinglu on 2017/9/15.
 */
public class SelectionSort extends Sort {
    public static int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int max = array[i];
            int index = i;
            for (int j = i + 1; j < array.length; j++) {
                if (less(max, array[j])) {
                    max = array[j];
                    index = j;
                }
            }
            exch(array, index, i);
        }
        return array;
    }
}
