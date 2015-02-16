package chat.chat_body;

import chat.client.ChatClient;
import chat.client.ChatPacket;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.ConnectException;
import java.util.Properties;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */
public class ChatFrame extends JFrame {

    public final int WIDTH = 700;
    public final int HEIGHT = 500;
    private final Color BACKGROUND_COLOR = new Color(245,255,240);
    private final Border BORDER = new EtchedBorder(EtchedBorder.RAISED);

    private JList userList;
    private JTextArea inputTextArea;
    private JTextField outputTextArea;
    private JButton sendButton;

    private ChatClient chatClient;
    private ChatPacket chatPacket;

    private PrintWriter pw;


    public ChatFrame(String title, ChatPacket chatPacket) throws HeadlessException {
        super(title);
        this.chatPacket = chatPacket;
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowCloseListener());
        init();
        Dimension screenCenter = SwingUtils.getScreenCenterSize(WIDTH, HEIGHT);
        setBounds(screenCenter.width, screenCenter.height, WIDTH, HEIGHT);
        setVisible(true);
        connectToServer();
    }

    private void init(){


        /* INPUT TEXT AREA */
        inputTextArea = doTextArea(600, 390);
        inputTextArea.setText("Type \"/enter\" to exit the chat.\n");
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        inputTextArea.setEditable(false);
        JScrollPane inputTextAreaScroll = doScrollPane(inputTextArea);

        /* USER LIST */
        userList = doList(150, 390);
        JScrollPane userListScroll = doScrollPane(userList);

        /* OUTPUT TEXT AREA */
        outputTextArea = doTextField(500, 50);
        outputTextArea.addKeyListener(new SendTextKeyListener());

        /* SEND BUTTON */
        sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonActionListener());

        /* SETTING BUTTONS */
        JButton saveLogsButton = doSettingButton(new ImageIcon("YuriyGorbylov\\src\\chat\\icons\\save.png"));
        saveLogsButton.addActionListener(new SaveLogsActionListener());

        JButton clearButton = doSettingButton(new ImageIcon("YuriyGorbylov\\src\\chat\\icons\\clear.png"));
        clearButton.addActionListener(new ClearActionListener());

        JButton historyButton = doSettingButton(new ImageIcon("YuriyGorbylov\\src\\chat\\icons\\history.png"));
        historyButton.addActionListener(new HistoryButtonActionListener());

        JButton exitButton = doSettingButton(new ImageIcon("YuriyGorbylov\\src\\chat\\icons\\exit.png"));
        exitButton.addActionListener(new ExitButtonActionListener() );



        /* PANELS */
        JPanel settingPanel = new JPanel(new GridLayout(13, 1, 0, 3));
        settingPanel.setBackground(BACKGROUND_COLOR);
        settingPanel.setBorder(BORDER);
        settingPanel.setPreferredSize(new Dimension(30, 390));
        settingPanel.add(saveLogsButton);
        settingPanel.add(clearButton);
        settingPanel.add(historyButton);
        settingPanel.add(exitButton);

        JPanel centerPanel = new JPanel(new BorderLayout(5,5));
        centerPanel.add(inputTextAreaScroll, BorderLayout.CENTER);
        centerPanel.setBorder(BORDER);

        JPanel userPanel = new JPanel(new BorderLayout(5,5));
        userPanel.setPreferredSize(new Dimension(150, 390));
        userPanel.add(userListScroll);
        userPanel.setBorder(BORDER);

        JPanel southPanel = new JPanel(new BorderLayout(5,5));
        southPanel.setPreferredSize(new Dimension(500, 35));
        southPanel.setBorder(BORDER);
        southPanel.add(outputTextArea, BorderLayout.CENTER);
        southPanel.add(sendButton, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(settingPanel, BorderLayout.WEST);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(userPanel, BorderLayout.EAST);
        mainPanel.add(southPanel, BorderLayout.SOUTH);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
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

    private JTextField doTextField(int width, int height){
        JTextField textField = new JTextField();
        textField.setBorder(BORDER);
        textField.setPreferredSize(new Dimension(width, height));
        textField.setBackground(BACKGROUND_COLOR);
        return textField;
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
            chatClient = new ChatClient(chatPacket, inputTextArea, userList);
            chatClient.sendMessage("hello");
            chatClient.readMessages();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Fail connection to the server", "Input error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            new LoginFrame("WELCOME!");
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
                if (message.equals("/exit")){
                    dispose();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class SendTextKeyListener extends KeyAdapter{

        @Override
        public void keyPressed(KeyEvent e) {
            final int KEY_ENTER = 10;
            if (e.getKeyCode() == KEY_ENTER){
                try {
                    String message = outputTextArea.getText();
                    chatClient.sendMessage(message);
                    outputTextArea.setText("");
                    if (message.equals("/exit")){
                        dispose();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    private class SaveLogsActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fileChooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files .txt", "txt");
            fileChooser.addChoosableFileFilter(filter);
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

    private class ClearActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            inputTextArea.setText("");
        }
    }

    private class HistoryButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                chatClient.sendMessage("/history");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class ExitButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                chatClient.sendMessage("/exit");
                dispose();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private class WindowCloseListener extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            try {
                chatClient.sendMessage("/exit");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
