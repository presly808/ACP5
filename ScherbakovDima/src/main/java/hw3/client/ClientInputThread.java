package hw3.client;

import hw3.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ClientInputThread implements Runnable{

    private InputStream inputStream;

    public ClientInputThread(InputStream inputStream){
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        while(true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                Message message = (Message) ois.readObject();
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
