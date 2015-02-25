package hw3.server;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class ServerOutputThread implements Runnable{

    private ClientsList clientsList;

    public ServerOutputThread(ClientsList clientsList){
        this.clientsList = clientsList;
    }

    @Override
    public void run() {
        Properties serverProperties = new Properties();

        try {
            serverProperties.load(new FileInputStream("ScherbakovDima/src/main/java/hw3/temp/server.properties"));
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(serverProperties.getProperty("port")));
            while(true){
                Socket socket = serverSocket.accept();
                clientsList.addClient(socket.getOutputStream());
                new Thread(new ServerInputThread(socket.getInputStream(), clientsList)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}