package chat.chat_body;

import chat.client.ChatClient;
import chat.client.ChatPacket;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */
public class ChatFrame extends JFrame {

    private final Color BACKGROUND_COLOR = new Color(245,255,240);
    private final Border BORDER = new EtchedBorder(EtchedBorder.RAISED);

    private JList userList;
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton sendButton;
    private ChatClient chatClient;
    private ChatPacket chatPacket;


    public ChatFrame(String title, ChatPacket chatPacket) throws HeadlessException {
        this.chatPacket = chatPacket;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800, 500);
        setResizable(false);
        init();
    }

    private void init(){
        try {
            chatClient = new ChatClient(chatPacket);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* INPUT TEXT AREA */
        inputTextArea = doTextArea(600,390);
        inputTextArea.setEditable(false);
        JScrollPane inputTextAreaScroll = doScrollPane(inputTextArea);

        /* USER LIST */
        userList = doList(200, 390);
        JScrollPane userListScroll = doScrollPane(userList);

        /* OUTPUT TEXT AREA */
        outputTextArea = doTextArea(500, 50);
        JScrollPane outputScroll = doScrollPane(outputTextArea);

        /* SEND BUTTON */
        sendButton = new JButton("Send");

        /* PANELS */

        JPanel centerPanel = new JPanel(new BorderLayout(5,5));
        centerPanel.add(inputTextAreaScroll, BorderLayout.CENTER);
        centerPanel.add(userListScroll, BorderLayout.EAST);
        centerPanel.setBorder(BORDER);

        JPanel sendButtonPanel = new JPanel();
        sendButtonPanel.add(sendButton);
        JPanel southPanel = new JPanel(new BorderLayout(5,5));
        southPanel.add(outputScroll, BorderLayout.CENTER);
        southPanel.add(sendButtonPanel, BorderLayout.EAST);


        setLayout(new BorderLayout(5, 5));
        JPanel mainPanel = new JPanel(new BorderLayout(5,5));
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        getContentPane().add(mainPanel);
    }

    private JScrollPane doScrollPane(Component c){
        JScrollPane scrollPane = new JScrollPane(c);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        return scrollPane;
    }

    private JTextArea doTextArea(int width, int height){
        JTextArea textArea = new JTextArea();
        textArea.setBorder(BORDER);
        textArea.setPreferredSize(new Dimension(width, height));
        textArea.setBackground(BACKGROUND_COLOR);
        return textArea;
    }

    private JList doList(int width, int height){
        JList list = new JList();
        list.setBorder(BORDER);
        list.setBackground(BACKGROUND_COLOR);
        list.setPreferredSize(new Dimension(width, height));
        return list;
    }
}
