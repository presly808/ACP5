package ua.artcode.week4.day1.concur;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by serhii on 09.02.15.
 */
public class TestLocksConcurrent {

    public static void main(String[] args) {
        DataHolder dh = new DataHolder(0);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 100; i++){
                        dh.addTo(1);
                    }
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 100; i++){
                        dh.get(1);
                    }
                }
            }).start();
        }

    }

}


class DataHolder {

    private final Lock lock = new ReentrantLock(true);
    private final Condition addCondition = lock.newCondition();
    private final Condition getCondition = lock.newCondition();
    private int data;

    public DataHolder(int data) {
        this.data = data;
    }

    public void addTo(int value){
        lock.lock();
        System.out.println(Thread.currentThread() + " add method get key " + data);
        try {
            while(data != 0){
                addCondition.await();
            }
            data = data + value;
            getCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread() + " add method release key " + data);
            lock.unlock();
        }
    }

    public void get(int value){
        lock.lock();
        System.out.println(Thread.currentThread() + " (get method) get key " + data);
        try {
            while(data < value){
                getCondition.await();
            }
            data = data - value;
            addCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread() + " (get method) release key " + data);
            lock.unlock();
        }
    }


}

