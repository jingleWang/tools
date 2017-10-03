package jingl.wang.java;

import jingl.wang.java.producer_customer.Customer;
import jingl.wang.java.producer_customer.Data;
import jingl.wang.java.producer_customer.Producer;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Ben on 25/09/2017.
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
        queue.put("1");
        System.out.println(1);
        queue.put("2");
        System.out.println(2);
        queue.put("3");
        System.out.println(3);
        System.out.println(queue.offer("4"));
        System.out.println(4);



    }

}
