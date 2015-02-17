package week4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dmartyuk on 09.02.2015.
 */
public class TestLocksConcurrent {

    public static void main(String[] args) {
        final DateHolder dh = new DateHolder(0);
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        dh.addTo(1);
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        dh.get(1);
                    }
                }
            }).start();
        }
    }
}

class DateHolder {
    
    private final Lock lock = new ReentrantLock(true);
    private final Condition addCondition = lock.newCondition();
    private final Condition getCondition = lock.newCondition();
    private int data;

    public DateHolder(int data) {
        this.data = data;
    }

    public void addTo(int value) {
        lock.lock();
        System.out.println(Thread.currentThread() + " (add method) get kay " + data);
        try {
            while (data != 0){
                addCondition.await();
            }
            this.data += value;
            getCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread() + " (add method) release kay " + data);
            lock.unlock();

        }
    }

    public void get (int value){
        lock.lock();
        System.out.println(Thread.currentThread() + " (get method) get kay " + data);

        try {
            while (data < value){
                getCondition.await();
            }
            data -= value;
            addCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread() + " (get method) release kay " + data);
            lock.unlock();
        }
    }
}