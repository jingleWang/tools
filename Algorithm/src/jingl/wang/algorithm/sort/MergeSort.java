package jingl.wang.algorithm.sort;

import java.util.Arrays;

/**
 * Created by Wang Jinglu on 2017/9/15.
 */
public class MergeSort extends Sort{
    public static int[] sort(int[] array) {
        int p = array.length / 2;
        part(array, 0, array.length-1);
        return array;
    }

    public static int[] part(int[] array, int f, int t) {

        if (f == t) {
            return null;
        }

        int p = f + (t - f) /2;

        //left include p
        part(array,f,p);

        //right
        part(array,p + 1, t);

        merge(array, f, t, p);
        return array;
    }

    public static void merge(int[] array, int from, int to, int p) {
        int[] temp = Arrays.copyOf(array,array.length);

        int i = from, j = p + 1;

        for (int k = from; k <= to; k++) {
            if (i > p) array[k] = temp[j++];
            else if (j>to) array[k] = temp[i++];
            else if (!less(temp[i],temp[j])) array[k] = temp[i++];
            else array[k] = temp[j++];
        }

    }


}
