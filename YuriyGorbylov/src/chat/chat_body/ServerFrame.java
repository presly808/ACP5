package chat.chat_body;

import chat.client.ChatPacket;
import chat.server.ChatServer;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Yuriy on 09.02.2015.
 */
public class ServerFrame extends JFrame{

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    private int port;
    private final Color BACKGROUND_COLOR = new Color(245,255,240);
    private final Border BORDER = new EtchedBorder(EtchedBorder.RAISED);

    public ServerFrame(String title, int port) throws HeadlessException {
        super(title);
        this.port = port;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        init();
        setVisible(true);
    }

    private void init(){

        JList userList = doList(275, 300);

        JList bannedUsersList = doList(275, 300);

        JButton moveButton = doButton(new ImageIcon("YuriyGorbylov\\src\\chat\\icons\\move.png"));
        JPanel buttonPanel = new JPanel(new GridLayout(10,1));
        buttonPanel.add(moveButton);


        JPanel listPanel = new JPanel(new BorderLayout(5,5));
        listPanel.add(userList, BorderLayout.WEST);
        listPanel.add(buttonPanel, BorderLayout.CENTER);
        listPanel.add(bannedUsersList, BorderLayout.EAST);

        JButton startButton = new JButton("START");
        startButton.addActionListener(new StartActionListener());
        JPanel startPanel = new JPanel();
        startPanel.add(startButton);

        getContentPane().add(listPanel, BorderLayout.CENTER);
        getContentPane().add(startPanel, BorderLayout.SOUTH);

    }

    private JList doList(int width, int height){
        JList list = new JList();
        list.setPreferredSize(new Dimension(width, height));
        list.setBorder(BORDER);
        list.setBackground(BACKGROUND_COLOR);
        return list;
    }

    private JButton doButton(Icon icon){
        JButton button = new JButton(icon);
        button.setPreferredSize(new Dimension(20, 20));
        return button;
    }

    private class StartActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                ChatServer chatServer = new ChatServer();
                chatServer.start();
            } catch (IOException e1) {
                System.out.println("SERVER DOES NOT WORK");
                e1.printStackTrace();
            }
        }
    }
}
