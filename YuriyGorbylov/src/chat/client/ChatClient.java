package chat.client;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {

    private ChatPacket chatPacket;
    private Socket socket;

    public ChatClient(ChatPacket chatPacket) throws IOException {
        this.chatPacket = chatPacket;
        Socket socket = new Socket(chatPacket.getIp(), Integer.valueOf(chatPacket.getPort()));
    }

    public void sendMessage(String message) throws IOException {

        PrintWriter out = new PrintWriter(socket.getOutputStream());
        String text = " " + chatPacket.getNick() + ": " + message;
        out.println(text);
        out.flush();
    }

    public void readMessage(){

        Thread read = new Thread(new ReadRunner(socket));
        read.start();
    }
}

