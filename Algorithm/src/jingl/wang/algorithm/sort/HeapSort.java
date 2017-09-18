package jingl.wang.algorithm.sort;

import java.util.Queue;

/**
 * Created by Ben on 18/09/2017.
 */
public class HeapSort extends Sort {
    public static int[] sort(int[] array) {
        for (int i = (array.length) / 2 - 1; i>=0; i--) {
            sink(array, i, array.length);
        }

        for (int i = array.length - 1; i > 0; i --) {
            exch(array, 0, i);
            sink(array, 0, i);

        }
        return array;
    }

    private static void swim(int[] data, int x) {
        if (x <= 0) return;

        int father = getFather(x);
        if (less(data[father], data[x])) {
            exch(data, father, x);
            swim(data, father);
        }
    }

    private static void sink(int[] data, int x, int size) {
        int son = getSon(x);
        if (son >= size) return;

        int maxSon = son;
        if (son + 1 < size) {
            maxSon = less(data[son],data[son+1]) ? son + 1 : son;
        }
        if (less(data[x], data[maxSon])) {
            exch(data, x, maxSon);
            sink(data,maxSon, size);
        }

    }

    private static int getFather(int x) {
        return (x - 1) / 2;
    }

    private static int getSon(int x) {
        return x * 2 +1;
    }

}
