package ua.artcode.week3.threads;

/**
 * Created by serhii on 03.02.15.
 */
public class ThreadTestCreation {

    public static void main(String[] args) {
        // main thread
        MyThreadExt thread1 = new MyThreadExt();
        System.out.println(thread1.getId() + " " + thread1.getName() + " " + thread1.getPriority() + " ");
        thread1.start();

        for (int i = 0; i < 100; i++) {
            System.out.println("Main thread " + i);
        }

    }

}

class MyThreadExt extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("New Thread " + i);
        }
    }
}

