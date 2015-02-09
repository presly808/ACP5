package chat.server;


import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuriy.gorbylev on 02.02.2015.
 */
public class ChatServer{

    public final int PORT = 33333;

    public void start() throws IOException {

        List<ChatConnection> connections = new ArrayList();
        ServerSocket ss = new ServerSocket(PORT);


        while(true) {
            try {
                Socket clientSocket = ss.accept();
                ChatConnection connection = new ChatConnection(clientSocket, connections);
                connections.add(connection);
                new Thread(connection).start();
            } catch (IOException e) {
                System.out.println("SERVER DOES NOT WORK");
                e.printStackTrace();
            }
        }
    }
}


