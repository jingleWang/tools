package jingl.wang.java.unSafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Ben on 02/10/2017.
 */
public class AtomicInteger {

    private volatile int value;

    private static long offset;

    private static final Unsafe unsafe;

    static {
        unsafe = getUnSafe();
        try {
            offset = unsafe.objectFieldOffset(AtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static Unsafe getUnSafe() {
        Unsafe theUnsafe;
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            theUnsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        return theUnsafe;
    }

    public AtomicInteger(int i) {
        value = i;
    }

    public int get() {return value; }

    public int addAndGet(int delta) {
        while (true) {
            int current = get();
            int next = current + delta;
            if (unsafe.compareAndSwapInt(this, offset, current, next)) {
                return next;
            }
        }
    }
}
