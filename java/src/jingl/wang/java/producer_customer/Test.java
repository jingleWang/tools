package jingl.wang.java.producer_customer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ben on 26/09/2017.
 */
public class Test {


//    public static void main(String[] args) {
//        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
//
//
//        Thread p = new Thread(new Runnable() {
//            int count = 0;
//            @Override
//            public void run() {
//                while (true) {
//                    if (count > 100) return;
//                    try {
//                        queue.put(count);
//                        System.out.println("add: " + count);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    count++;
//                }
//            }
//        });
//        p.start();
//
//        Thread c = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        int v = queue.take();
//                        System.out.println("delete: " + v);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        c.start();
//
//    }

    public static void main(String[] args) throws InterruptedException {
        final AtomicInteger data = new AtomicInteger(0);
        new Thread(new Runnable() {
            public void run() {
                try {
                    data.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.addAndGet(1);
            }
        }).start();
        synchronized (data) {
            data.notify();
            Thread.sleep(100000);
        }
    }
}




























