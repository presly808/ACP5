package week3.threads;

/**
 * Created by dmartyuk on 03.02.2015.
 */
public class TestCommonResourceThreads {

    public static void main(String[] args) throws InterruptedException {
        BankAccount acc = new BankAccount(0);

        for (int i = 0; i < 2; i++) {
            Thread t1 = new Thread(new DepositMoney(acc));
            Thread t2 = new Thread(new GetMoneyThread(acc));
            t1.start();
            t2.start();

        }

//        t1.join();
//        t2.join();
        System.out.println(acc.getCash());
    }
}

class DepositMoney implements Runnable {

    private BankAccount account;

    public DepositMoney(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.deposit(1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class GetMoneyThread implements Runnable{

    private BankAccount account;

    public GetMoneyThread(BankAccount account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            account.withdraw(1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class BankAccount {

    private int cash;
    private static final int LIMIT = 1000;
    public BankAccount(int cash) {
        this.cash = cash;
    }
    public synchronized void deposit(int money){
        System.out.printf("deposit: %s get monitor\n", Thread.currentThread().getName());
        while(cash >= LIMIT){
            try {
                System.out.printf("deposit: %s to sleep\n", Thread.currentThread().getName());
                this.wait();
                System.out.printf("deposit: %s awake\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("deposit: %s put money = %d [%d]\n", Thread.currentThread().getName(), cash, money);
        cash = cash + money;
        this.notifyAll();
    }

    public synchronized int withdraw(int money){
        System.out.printf("withdraw: %s withdraw get monitor\n", Thread.currentThread().getName());
        while(cash <= 0){
            try {
                System.out.printf("withdraw: %s to sleep\n", Thread.currentThread().getName());
                this.wait();
                System.out.printf("withdraw: %s awake\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("withdraw: %s to sleep\n", Thread.currentThread().getName());
        System.out.printf("withdraw: %s cash[%d], money[%d]\n", Thread.currentThread().getName(), cash, money);
        cash = cash - money;
        System.out.printf("withdraw: %s withdraw money = %d\n", Thread.currentThread().getName(), cash);
        this.notifyAll();
        return money;
    }

    public int getCash() {
        return cash;
    }
}