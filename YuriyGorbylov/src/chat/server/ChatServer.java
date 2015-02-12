package chat.server;


import chat.client.ChatPacket;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by yuriy.gorbylev on 02.02.2015.
 */
public class ChatServer extends Thread{

    private ServerSocket ss;
    private List<ChatConnection> syncConnections = Collections.synchronizedList(new ArrayList());
    private List<String> syncUsers = Collections.synchronizedList(new ArrayList());
    private int port;

    public ChatServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        try {
            ServerSocket ss = new ServerSocket(port);
            while(true) {

                    Socket clientSocket = ss.accept();
                    ChatConnection chatConnection = new ChatConnection(clientSocket);
                    syncConnections.add(chatConnection);
                    new Thread(chatConnection).start();
            }
        } catch (IOException e) {
            System.out.println("SERVER DOES NOT WORK");
            e.printStackTrace();
        }
    }

    public void stopServer(){

    }


    private class ChatConnection implements Runnable {

        private ObjectOutputStream out;
        private ObjectInputStream in;
        private Socket clientSocket;
        private ChatPacket chatPacket;
        private ServerPacket serverPacket;
        private boolean isConnectingMessage = true;
        private String message;

        public ChatConnection(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {

            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());

                while(true){
                    chatPacket = (ChatPacket) in.readObject();

                    if (isConnectingMessage){
                        SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss: ");
                        message = date.format(new Date()) + chatPacket.getNick() + ". IP: " +
                                clientSocket.getInetAddress().getHostAddress() + ". Has just connected.";
                        isConnectingMessage = false;
                        syncUsers.add(chatPacket.getNick());
                    }else{
                        message = chatPacket.getNick() + ": " + chatPacket.getMessage();
                    }

                    System.out.println(message);
                    serverPacket = new ServerPacket(message, syncUsers);
                    for (ChatConnection connection : syncConnections){
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
}


