package week3.threads;

/**
 * Created by dmartyuk on 03.02.2015.
 */
public class ThreadTestCreation {
    public static void main(String[] args) {
        MyThredExt thred1 = new MyThredExt();
        thred1.setName("My litle thread :)");
        System.out.println(thred1.getId() + " [\"" + thred1.getName() + "\"] " + thred1.getPriority());
        thred1.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("Main thread " + i);
        }
    }


}


class MyThredExt extends Thread {

    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("New thread " + i);
        }
    }
}