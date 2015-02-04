package chat.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yuriy.gorbylev on 02.02.2015.
 */
public class ChatConnection implements Runnable {

    private Socket clientSocket;
    private Scanner in;
    private PrintWriter out;
    List<ChatConnection> connections;

    public ChatConnection(Socket socket, List connections) {
        this.clientSocket = socket;
        this.connections = connections;
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        System.out.printf("Address = %s\n", clientSocket.getInetAddress().toString());
        out.println("Welcome to CHAT!");

        while(in.hasNextLine()){
            String message = in.nextLine();
            System.out.println(message);
            for (ChatConnection connection : connections){              // Отправялю всем соединениям то, что получил сервер
                connection.out.println(message);
            }
        }
    }
}

