package ua.artcode.week3.threads;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by serhii on 03.02.15.
 */
public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        List<Thread> menThread = new LinkedList<>();

        for (int i = 0; i < 5; i++) {
            // Inner Anonymous class
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(
                                Thread.currentThread().getName() + " " + j);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.setDaemon(true);
            menThread.add(thread);
            thread.start();
        }

        /*Thread thread = menThread.get(0);
        thread.join(); // main thread wait until thread finish his work
        System.out.println("after join");*/


    }

}
