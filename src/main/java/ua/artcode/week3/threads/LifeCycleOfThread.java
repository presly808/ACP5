package ua.artcode.week3.threads;

/**
 * Created by serhii on 03.02.15.
 */
public class LifeCycleOfThread {

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);//new
        System.out.println(thread.getState());
        thread.start();

        while(thread.isAlive()){
            System.out.println(thread.getState()); //now is working
            Thread.sleep(500);
        }
    }

}

// purpose -
class MyThread implements Runnable {

    @Override
    public void run() {
        Thread th = Thread.currentThread();
        // like this word
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

