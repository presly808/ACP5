package chat.client;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private ChatPacket chatPacket;
    private Socket socket;
    private Scanner in;
    private PrintWriter out;

    public ChatClient(ChatPacket chatPacket, JTextArea inputTextArea, JTextArea outputTextArea) throws IOException {
        this.chatPacket = chatPacket;
        this.inputTextArea = inputTextArea;
        this.outputTextArea = outputTextArea;
        this.socket = new Socket(chatPacket.getIp(), Integer.valueOf(chatPacket.getPort()));
        ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
        ous.writeObject(chatPacket);
        ous.flush();
    }

    public void sendMessage(String message) throws IOException {
        out = new PrintWriter(socket.getOutputStream());
        out.println(chatPacket.getNick() + ": " + message);
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
                in = new Scanner(socket.getInputStream());
                while (in.hasNextLine()) {
                    String message = in.nextLine();
                    System.out.println(message);
                    inputTextArea.append(message + "\n");

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

