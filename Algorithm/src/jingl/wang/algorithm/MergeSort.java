package jingl.wang.algorithm;

/**
 * Created by Wang Jinglu on 2017/9/15.
 */
public class MergeSort {
    public static int[] sort(int[] array) {
        int p = array.length / 2;

    }

    public static int[] part(int[] array, int f, int t) {

        if (f == t) {
            int[] temp = new int[1];
            temp[0] = array[f];
            return temp;
        }

        int p = (f + t) /2;

        //left include p
        int[] left = part(array,f,p);

        //right
        int[] right = part(array,p + 1, t);


    }

    public static int[] merge(int[] a, int[] b) {
        int[] newArray = new int[a.length + b.length];
    }


}
