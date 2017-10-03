package jingl.wang.java.classLoader;

import com.sun.nio.zipfs.ZipInfo;
import jingl.wang.algorithm.sort.HeapSort;
import jingl.wang.algorithm.sort.MergeSort;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.LinkedList;

/**
 * Created by Ben on 26/09/2017.
 */
public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println("heapsort:"+HeapSort.class.getClassLoader().getClass().getName());
        System.out.println("Test:"+Test.class.getClassLoader().getClass().getName());
//        System.out.println("test Parent:"+Test.class.getClassLoader().getParent().getClass().getClassLoader().getClass().getName());
        LinkedList linkedList = new LinkedList();
        System.out.println("zipInfo:"+ZipInfo.class.getClassLoader().getClass().getName());



    }

}
