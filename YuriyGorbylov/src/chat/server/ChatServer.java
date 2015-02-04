package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
        import java.util.List;

/**
 * Created by yuriy.gorbylev on 02.02.2015.
 */
public class ChatServer implements Runnable{

    public static final int PORT = 49001;
    public static final String IP = "10.131.0.97";
    private List<ChatConnection> connections = new ArrayList<>();

    @Override
    public void run() {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(true){
            try {

                Socket clientSocket = ss.accept();
                ChatConnection connection = new ChatConnection(clientSocket, connections);
                connections.add(connection);
                new Thread(connection).start();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}


