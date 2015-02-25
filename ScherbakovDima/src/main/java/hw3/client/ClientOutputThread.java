package hw3.client;

import hw3.Message;
import hw3.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Scanner;

public class ClientOutputThread implements Runnable{

    private OutputStream outputStream;
    //private User user;

    public ClientOutputThread(OutputStream outputStream){
        this.outputStream = outputStream;
        //this.user = user;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            Scanner scanner = new Scanner(System.in);
            //oos.writeObject(user);


            while (true) {
                System.out.print("Print message - ");
                String textMessage = scanner.next();
                Message message = new Message("User", new Date(), textMessage);
                oos.writeObject(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}