package jingl.wang.java.unSafe;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Ben on 02/10/2017.
 */
public class Test {
    static int value = 0;
    static AtomicInteger ai = new AtomicInteger(0);
    public static void main(String[] args) {

        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String, String>(1);
        map.put("test", "test");


        System.out.println(Integer.toBinaryString(1));
        System.out.println(Integer.toBinaryString(-1>>>1));
//        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(100);
//        for (int i = 0; i<1000000; i++) {
//            executor.submit(new Runnable() {
//                public void run() {
////                    value++;
//                    ai.addAndGet(1);
//                    System.out.println(ai.get());
//                }
//            });
//        }
//        Thread.sleep(10000);
//        System.out.println("\n\n\nfinal v:" + ai.get());
    }
}
