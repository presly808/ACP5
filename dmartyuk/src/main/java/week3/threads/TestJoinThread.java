package week3.threads;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dmartyuk on 03.02.2015.
 */
public class TestJoinThread {

    public static void main(String[] args) throws InterruptedException {
        List<Thread> lt = new LinkedList<Thread>();
        for (int i = 0; i < 5; i++) {
            Thread th = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        System.out.println(Thread.currentThread().getName() + " " + j);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            th.setDaemon(true);
            lt.add(th);
            th.start();
        }

//        Thread thread = lt.get(0);
//        thread.join(); // мейн(главный) поток ждет когда поток не закончит свою работу
//        System.out.println("after join");

    }
}
//
//class MyJoinedThread implements Runnable {
//
//    @Override
//    public void run() {
//
//    }
//}