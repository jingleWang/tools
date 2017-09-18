package jingl.wang.algorithm.queue;

import jingl.wang.algorithm.sort.Test;

/**
 * Created by Ben on 18/09/2017.
 */
public class PriorityQueue {
    int[] data;

    int capacity;
    int size;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        data = new int[capacity];
        size = 0;
    }

    public void insert(int value) {
        data[size++] = value;
        swim(size-1);
    }

    public int getMax() {
        int v = data[0];
        if (size<=0) {return -1;}
        data[0] = data[size-1];
        sink(0);
        return v;
    }

    private void exch(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    private int getFather(int x) {
        return (x - 1) / 2;
    }

    private int getSon(int x) {
        return x * 2 +1;
    }

    private void swim(int x) {
        if (x <= 0) return;

        int father = getFather(x);
        if (less(data[father], data[x])) {
            exch(data, father, x);
            swim(father);
        }
    }

    private void sink(int x) {
        int son = getSon(x);
        if (son > size) return;

        int maxSon = son;
        if (son + 1 < size) {
            maxSon = less(data[son],data[son+1]) ? son + 1 : son;
        }
        if (less(data[x], data[maxSon])) {
            exch(data, x, maxSon);
            sink(maxSon);
        }

    }

    private boolean less(int a, int b) {
        return a < b;
    }

    public static void main(String[] args) {
        PriorityQueue  q = new PriorityQueue(10);
        int[] data = Test.getArray(10);
        Test.print(data);
        for (int i : data) {
            q.insert(i);
        }

        System.out.println();
        for (int i = 0; i<10; i++) {
            System.out.print(q.getMax() + "  ");
        }
    }
}
