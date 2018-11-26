package jingl.wang.java.producer_customer;

import sun.jvm.hotspot.utilities.Assert;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Ben on 26/09/2017.
 */
public class Test {

    public static Unsafe unsafe = getUnSafe();


    public static void main(String[] args) throws InterruptedException {
        AThread aThread = new AThread();
        Thread thread = new Thread(aThread);

        AThread aThread1 = new AThread();
        Thread thread1 = new Thread(aThread1);
//        thread.start();
        thread1.start();

        Thread.sleep(1000);
        AThread.lock = 1;
        System.out.println(AThread.lock);
    }

    public static class AThread implements Runnable {

        static int lock;
        Integer value;
        static int i = 0;

        public AThread() {
            this.lock = 0;
            this.value = 0;
        }

        public void run() {
//            add(); //累加
            test1();
        }

        public void test1() {
            while (lock == 0) {

                int l = unsafe.getInt(AThread.class, lockOffset);
//                System.out.println(l);
            }

//            int l = unsafe.getInt(AThread.class, lockOffset);
//            System.out.println(l);
            System.out.println("thread end");
        }

        public void add() {
            for (int j = 0; j < 100000; j++) {
                while (!tryAccquire()) {
                }
                i++;
                release();
            }
        }

        public static boolean tryAccquire() {
            return unsafe.compareAndSwapInt(AThread.class, lockOffset, 0, 1);
        }

        public static void release() {
            unsafe.putInt(AThread.class, lockOffset, 0);
        }

        private static long lockOffset;

        static {
            try {
                lockOffset = unsafe.staticFieldOffset(AThread.class.getDeclaredField("lock"));
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }


    public static Unsafe getUnSafe() {
        Field f = null; //Internal reference
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            Unsafe unsafe = (Unsafe) f.get(null);
            return unsafe;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}




























