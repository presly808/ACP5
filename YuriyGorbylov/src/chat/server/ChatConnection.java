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
    private ObjectInputStream in;
    private ServerPacket serverPacket;
    private ChatPacket chatPacket;
    private List<String> users;
    private List<ChatConnection> connections;
    private boolean isConnectingMessage = true;
    private String message;

    public ChatConnection(Socket clientSocket, List<ChatConnection> connections, List<String> users) {
        this.clientSocket = clientSocket;
        this.connections = connections;
        this.users = users;
    }

    @Override
    public void run() {

        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());

            while(true){
                chatPacket = (ChatPacket) in.readObject();

                if (isConnectingMessage){
                    SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss: ");
                    message = date.format(new Date()) + chatPacket.getNick() + ". IP: " +
                            clientSocket.getInetAddress().getHostAddress() + ". Has just connected." + chatPacket.getMessage();
                    isConnectingMessage = false;
                }else{
                    message = chatPacket.getMessage();
                }

                System.out.println(message);
                users.add(chatPacket.getNick());
                serverPacket = new ServerPacket(message, users);
                for (ChatConnection connection : connections){
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

