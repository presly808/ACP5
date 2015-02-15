package ua.artcode.week3.threads;

import java.util.Date;

/**
 * Created by serhii on 03.02.15.
 */
public class TestCommonResourceThreads {

    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(0);

        for(int i = 0; i < 10; i++){

            Thread th1 = new Thread(new DepositTread(account));
            Thread th2 = new Thread(new GetMoneyThread(account));
            th1.start();
            th2.start();

        }

        //System.out.println(account.getCash());
    }
}

class DepositTread implements Runnable {

    private BankAccount account;

    public DepositTread(BankAccount account) {
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

class GetMoneyThread implements Runnable {

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

    private static final int LIMIT = 1000;
    private int cash;

    public BankAccount(int cash) {
        this.cash = cash;
    }

    public synchronized void deposit(int money) {
        System.out.printf("%s deposit get monitor\n", Thread.currentThread().getName());
        while(cash >= LIMIT){
            try {
                System.out.printf("%s deposit going to sleep\n", Thread.currentThread().getName());
                this.wait();
                System.out.printf("%s awake\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("%s put money=%d\n", Thread.currentThread().getName(),cash);
        cash = cash + money;
        notifyAll();
    }

    public synchronized int withdraw(int money) {
        System.out.printf("%s withdraw get monitor\n", Thread.currentThread().getName() );
        while(cash <= 0){
            try {
                System.out.printf("%s withdraw going to sleep\n", Thread.currentThread().getName() );
                this.wait();
                System.out.printf("%s withdraw awake\n", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("%s withdraw money = %d\n", Thread.currentThread().getName(), cash);
        cash = cash - money;
        this.notifyAll();
        return money;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }
}

