package week3.threads;

/**
 * Created by dmartyuk on 03.02.2015.
 */
public class LifeCycleOfThread {
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread t = new Thread(myThread);
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());

        while (t.isAlive()){
            System.out.println(t.getState());

            Thread.sleep(500);
        }
    }
}


class MyThread implements Runnable {

    @Override
    public void run() {
        Thread th = Thread.currentThread();
        th.setName(this.getClass().getName());
        for (int i = 0; i < 10; i++) {
            System.out.println(th.getName() + "=" + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}