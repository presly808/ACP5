package week3.home.core.server;

import week3.home.core.utils.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by dmartyuk on 09.02.2015.
 */
public class WorkerUser implements IUser, Runnable {

    private Socket userSocket;
    private IServer server;
    private String ip;

    public WorkerUser(Socket userSocket, IServer server, String ip) {
        this.userSocket = userSocket;
        this.server = server;
        this.ip = ip;
    }

    @Override
    public void sendMessageToClient(Message message) {

    }

    @Override
    public void run() {
        try {
            ObjectInputStream input = new ObjectInputStream(userSocket.getInputStream()); // входящие сообщения
            ObjectOutputStream output = new ObjectOutputStream(userSocket.getOutputStream()); // исходящие сообщения
            Message message;
            while(!Thread.currentThread().isInterrupted()){
                message = (Message) input.readObject();
                output.writeObject(message);
                Thread.sleep(500);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Thread.currentThread().interrupt();
        }
    }
}
