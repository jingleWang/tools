package jingl.wang.java.producer_customer;

import java.util.Date;

/**
 * Created by Ben on 25/09/2017.
 */
public class Customer extends Thread {
    private Data data;
    int count = 0;

    public Customer(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            if (count > 20) return;
            synchronized (data) {
                if (data.value == 0) {
                    try {
                        data.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                data.value--;
                System.out.println(Thread.currentThread().getName() + ":reduce " + data.value);
                count++;
            }
        }
    }
}
