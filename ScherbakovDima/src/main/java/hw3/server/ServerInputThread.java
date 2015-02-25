package hw3.server;

import hw3.Message;
import hw3.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class ServerInputThread implements Runnable{

    private InputStream inputStream;
    private ClientsList clientsList;

    public ServerInputThread(InputStream inputStream, ClientsList clientsList){
        this.inputStream = inputStream;
        this.clientsList = clientsList;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            //User user = (User)ois.readObject();
            Message message = (Message) ois.readObject();
            clientsList.send(message);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
