package chat.client;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */

import chat.server.ServerPacket;

import javax.swing.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ChatClient {

    private JTextArea inputTextArea;
    private JList userList;
    private ChatPacket chatPacket;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ChatClient(ChatPacket chatPacket, JTextArea inputTextArea, JList userList) throws IOException {
        this.chatPacket = chatPacket;
        this.inputTextArea = inputTextArea;
        this.userList = userList;
        this.socket = new Socket(chatPacket.getIp(), Integer.valueOf(chatPacket.getPort()));

        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
    }

    public void sendMessage(String message) throws IOException {
        ChatPacket messagePacket = new ChatPacket(null, null, this.chatPacket.getNick(), message);
        out.writeObject(messagePacket);
        out.flush();
    }

    public void readMessages(){

        Thread read = new Thread(new ReadRunner());
        read.start();
    }


    private class ReadRunner implements Runnable{

        @Override
        public void run() {

            try {
                in = new ObjectInputStream(socket.getInputStream());
                while(true){
                    ServerPacket serverPacket = (ServerPacket) in.readObject();
                    inputTextArea.append(serverPacket.getMessage() + "\n");
                    List<String> list = serverPacket.getList();
                    userList.setListData(list.toArray());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

