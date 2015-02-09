package week4.day1.concur;

import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Yuriy on 09.02.2015.
 */
public class TestLocksConcurrent {

    public static void main(String[] args) {
        final DataHolder dataHolder = new DataHolder(0);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        dataHolder.addTo(1);
                    }
                }
            }).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 100; j++) {
                        dataHolder.get(1);
                    }
                }
            }).start();
        }
    }
}

class DataHolder {
    private int data;
    private final Lock lock = new ReentrantLock(true);
    private final Condition addCondition = lock.newCondition();
    private final Condition getCondition = lock.newCondition();

    public DataHolder(int data) {
        this.data = data;
    }

    public void addTo(int value){
        lock.lock();
        System.out.println(Thread.currentThread() + " get key " + data);
        try {
            while(data != 0){
                addCondition.await();
            }
            data = data + value;
            getCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            lock.unlock();
            System.out.println(Thread.currentThread() + " release key " + data);
        }
    }

    public void get(int value){
        lock.lock();
        System.out.println(Thread.currentThread() + " (get method) get key " + data);
        try{
            while(data < value){
                getCondition.await();
            }
            data = data - value;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread() + " (get method) release key " + data);
            lock.unlock();
        }
    }
}
