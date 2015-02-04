package chat.client;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private ChatPacket chatPacket;
    private Socket socket;
    private ObjectOutputStream ous;
    private Scanner in;

    public ChatClient(ChatPacket chatPacket) throws IOException {
        this.chatPacket = chatPacket;
        this.socket = new Socket(chatPacket.getIp(), Integer.valueOf(chatPacket.getPort()));
        in = new Scanner(socket.getInputStream());
        OutputStream fos = socket.getOutputStream();
        ous = new ObjectOutputStream(fos);
        ous.writeObject(chatPacket);
        ous.flush();
    }

    public void sendMessage(String message) throws IOException {


    }

    public void readMessage(){

        Thread read = new Thread(new ReadRunner());
        read.start();
    }


    private class ReadRunner implements Runnable{

        @Override
        public void run() {
            while (in.hasNextLine()) {
                System.out.println(in.nextLine());
                chatPacket.getInputTextArea().append(in.nextLine());
            }
        }
    }
}

