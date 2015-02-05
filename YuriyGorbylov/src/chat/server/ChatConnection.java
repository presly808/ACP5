package chat.server;

import chat.client.ChatPacket;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yuriy.gorbylev on 02.02.2015.
 */
public class ChatConnection implements Runnable {

    private Socket clientSocket;
    private PrintWriter out;
    private Scanner in;
    private List<ChatConnection> connections;

    public ChatConnection(Socket clientSocket, List connections) {
        this.clientSocket = clientSocket;
        this.connections = connections;
    }

    @Override
    public void run() {

        try {
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ChatPacket packet = (ChatPacket) ois.readObject();
            SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss: ");
            String connectingMessage = date.format(new Date()) + packet.getNick() + ". IP: " +  packet.getIp() + ". Has just connected.";
            System.out.println(connectingMessage);
            out = new PrintWriter(clientSocket.getOutputStream());
            for (ChatConnection connection : connections){              // Отправялю всем соединениям то, что получил сервер
                connection.out.println(connectingMessage);
                connection.out.flush();
            }

            in = new Scanner(clientSocket.getInputStream());
            while(in.hasNextLine()){
                String message = in.nextLine();
                System.out.println(message);
                for (ChatConnection connection : connections){              // Отправялю всем соединениям то, что получил сервер
                    connection.out.println(message);
                    connection.out.flush();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}

