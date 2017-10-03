package jingl.wang.java.producer_customer;


/**
 * Created by Ben on 25/09/2017.
 */
public class Producer extends Thread {

    private Data data;
    int count = 0;

    public Producer(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            if (count > 20) return;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (data) {
                data.value++;
                data.notify();
                count++;
                System.out.println(Thread.currentThread().getName() + ":add " + data.value);
            }
        }
    }
}
