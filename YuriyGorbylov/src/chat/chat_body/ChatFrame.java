package chat.chat_body;

import chat.client.ChatClient;
import chat.client.ChatPacket;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Properties;

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

    PrintWriter pw;

    public ChatFrame(String title, ChatPacket chatPacket) throws HeadlessException {
        super(title);
        this.chatPacket = chatPacket;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800, 500);
        setResizable(false);
        init();
        connectToServer();
    }

    private void init(){


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
        sendButton.addActionListener(new SendButtonActionListener());

        /* SETTING BUTTONS */
        JButton saveLogsButton = doSettingButton(new ImageIcon("YuriyGorbylov\\src\\chat\\icons\\save.png"));
        saveLogsButton.addActionListener(new SaveLogsActionListener());

        JButton clearButton = doSettingButton(new ImageIcon("YuriyGorbylov\\src\\chat\\icons\\clear.png"));
        clearButton.addActionListener(new ClearActionListener());



        /* PANELS */
        JPanel settingPanel = new JPanel(new GridLayout(13, 1, 0, 3));
        settingPanel.setBackground(BACKGROUND_COLOR);
        settingPanel.setPreferredSize(new Dimension(25, 390));
        settingPanel.add(saveLogsButton);
        settingPanel.add(clearButton);

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
        mainPanel.add(settingPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));

        getContentPane().add(mainPanel);
    }

    /* PATTERNS */

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

    private JButton doSettingButton(Icon icon){
        JButton button = new JButton(icon);
        button.setBackground(BACKGROUND_COLOR);
        button.setPreferredSize(new Dimension(20, 20));
        button.setBorder(null);
        return button;
    }

    private void connectToServer(){
        try {
            chatClient = new ChatClient(chatPacket, inputTextArea, outputTextArea);
            chatClient.readMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* LISTENERS */

    private class SendButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String message = outputTextArea.getText();
                chatClient.sendMessage(message);
                outputTextArea.setText("");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class SendTextActionListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            final int KEY_ENTER = 10;
            if (e.getKeyCode() == KEY_ENTER){
                try {
                    String message = outputTextArea.getText();
                    chatClient.sendMessage(message);
                    outputTextArea.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public class SaveLogsActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setFileFilter(new TxtFileFilter());
            fileChooser.showSaveDialog(null);
            File chosenFile = fileChooser.getSelectedFile();
            try {
                pw = new PrintWriter(chosenFile);
                pw.println(inputTextArea.getText());
                pw.flush();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Chose a file or create your own", "File error", JOptionPane.ERROR_MESSAGE);
            }finally {
                pw.close();
            }
        }
    }

    public class ClearActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            inputTextArea.setText("");
        }
    }

    private class TxtFileFilter extends FileFilter{

        @Override
        public boolean accept(File f) {
            String str = f.getName();
            return str.substring(str.length()-3).equals(".txt") || f.isDirectory();
        }

        @Override
        public String getDescription() {
            return ".txt";
        }
    }
}
