package chat.server;

import chat.client.ChatPacket;

import java.io.*;
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
    private ObjectOutputStream out;
    private Scanner in;
    private ServerPacket serverPacket;
    private List<String> users;
    private List<ChatConnection> connections;

    public ChatConnection(Socket clientSocket, List<ChatConnection> connections, List<String> users) {
        this.clientSocket = clientSocket;
        this.connections = connections;
        this.users = users;
    }

    @Override
    public void run() {

        try {
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ChatPacket packet = (ChatPacket) ois.readObject();
            SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss: ");
            String connectingMessage = date.format(new Date()) + packet.getNick() + ". IP: " +
                    clientSocket.getInetAddress().getHostAddress() + ". Has just connected.";
            System.out.println(connectingMessage);
            users.add(packet.getNick());

            out = new ObjectOutputStream(clientSocket.getOutputStream());
            serverPacket = new ServerPacket(connectingMessage, users);
            for (ChatConnection connection : connections){              // Отправялю всем соединениям то, что получил сервер
                connection.out.writeObject(serverPacket);
                connection.out.flush();
            }

            in = new Scanner(clientSocket.getInputStream());
            while(in.hasNextLine()){
                String message = in.nextLine();
                System.out.println(message);
                for (ChatConnection connection : connections){              // Отправялю всем соединениям то, что получил сервер
                    connection.out.writeObject(serverPacket);
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

