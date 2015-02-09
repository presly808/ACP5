package chat.chat_body;

import chat.client.ChatPacket;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Yuriy on 09.02.2015.
 */
public class ServerFrame extends JFrame{

    private int port;

    public ServerFrame(String title, int port) throws HeadlessException {
        super(title);
        this.port = port;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 400);
        setResizable(false);
        init();
        setVisible(true);
    }

    private void init(){

    }
}
