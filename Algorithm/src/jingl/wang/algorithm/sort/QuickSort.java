package jingl.wang.algorithm.sort;

/**
 * Created by Ben on 20/09/2017.
 */
public class QuickSort extends Sort{
    public static int[] sort(int[] array) {
        sort(array, 0, array.length - 1);
        return array;
    }

    public static void sort(int[] array, int lo, int hi) {
        if (hi <= lo) return;
        int p = part(array, lo, hi);
        sort(array, lo, p - 1);
        sort(array, p + 1, hi);
    }

    public static int part(int[] array, int lo, int high) {
        int p = array[lo];
        int i = lo;
        int j = high+1;
        while (true) {
            while (less((array[++i]), p)) {
                if (i==high) break;
            }

            while (less(p, array[--j])) {
                if (lo==j) break;
            }
            if (i>=j)
                break;
            exch(array, i, j);
        }
        exch(array, lo, j);
        return i;
    }
}
