package jingl.wang.algorithm;

/**
 * Created by Wang Jinglu on 2017/9/15.
 */
public class HellSort extends Sort {
    public static int[] sort(int[] array) {

        for( int step = array.length/2; step>0;step/=2) {
            for (int i = 0; i< step;i++) {
                for (int j = i + step; j<array.length; j++) {
                    while (less(array[j - step], array[j])) {
                        exch(array,j-step,j);
                    }
                }
            }
        }
        return array;
    }


}
