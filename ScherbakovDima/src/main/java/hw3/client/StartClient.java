package hw3.client;

import hw3.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Properties;

/**
 * Created by Templer on 23.02.2015.
 */
public class StartClient {
    public static void main(String[] args) {
        //User user = new User("152.163.2.9", 9050, "templer", "1234");
        Properties serverProperties = new Properties();

        try {
            serverProperties.load(new FileInputStream("ScherbakovDima/src/main/java/hw3/temp/server.properties"));
            String ip = serverProperties.getProperty("ip");
            int port = Integer.parseInt(serverProperties.getProperty("port"));
            Socket socket = new Socket(ip, port);

            new Thread(new ClientOutputThread(socket.getOutputStream())).start();
            new Thread(new ClientInputThread(socket.getInputStream())).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
