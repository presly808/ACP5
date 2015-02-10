package chat.chat_body;

import chat.client.ChatPacket;
import chat.server.ChatServer;
import chat.validators.IPValidator;
import chat.validators.NickValidator;
import chat.validators.PortValidator;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */
public class LoginFrame extends JFrame{

    private final Font FONT = new Font("Calibri", Font.BOLD, 20);

    private JTextField ipTextField;
    private JTextField portTextField;
    private JTextField nickTextField;
    private JLabel serverTextLabel;
    private JButton startButton;

    public LoginFrame(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(400, 200);
        setResizable(false);
        init();
        setVisible(true);

    }

    private void init(){

        /* CLIENT PANEL PART */

        /* Welcome Label Panel */
        JPanel welcomePanel = new JPanel();
        JLabel welcomeLabel = doLabel("Welcome to CHAT!");
        welcomeLabel.setFont(FONT);
        welcomePanel.add(welcomeLabel);

        /* Text fields panel */
        GridLayout clientPanelGridLayout = new GridLayout(2,3);
        clientPanelGridLayout.setHgap(5);
        clientPanelGridLayout.setVgap(0);
        JPanel clientTextPanel = new JPanel(clientPanelGridLayout);
        clientTextPanel.setBorder(new TitledBorder("IP/PORT"));

        JLabel ipLabel = doLabel("IP");
        JLabel portLabel = doLabel("PORT");
        JLabel nickLabel = doLabel("Nick");

        clientTextPanel.add(ipLabel);
        clientTextPanel.add(portLabel);
        clientTextPanel.add(nickLabel);

        ipTextField = new JTextField("localhost");
        ipTextField.setHorizontalAlignment(SwingConstants.CENTER);
        portTextField = new JTextField("33333");
        portTextField.setHorizontalAlignment(SwingConstants.CENTER);
        nickTextField = new JTextField("Yura");
        nickTextField.setHorizontalAlignment(SwingConstants.CENTER);
        clientTextPanel.add(ipTextField);
        clientTextPanel.add(portTextField);
        clientTextPanel.add(nickTextField);


        /* Button Panel */
        startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonActionListener());
        JPanel clientButtonPanel = new JPanel(new GridLayout(1,3));
        clientButtonPanel.add(new Label());
        clientButtonPanel.add(startButton);
        clientButtonPanel.add(new Label());

        /* Client Panel */
        JPanel clientPanel = new JPanel(new BorderLayout(5, 0));
        clientPanel.add(welcomePanel, BorderLayout.NORTH);
        clientPanel.add(clientTextPanel, BorderLayout.CENTER);
        clientPanel.add(clientButtonPanel, BorderLayout.SOUTH);


        /* SERVER PANEL PART */

        serverTextLabel = doLabel("Press \"Start\" to start the server.");
        JLabel serverPortLabel = doLabel("Port = 33333");

        JButton serverStartButton = new JButton("Start");
        serverStartButton.addActionListener(new StartServerButtonActionListener());

        JPanel serverPanel = new JPanel(new GridLayout(3,1,5,5));
        serverPanel.add(serverTextLabel);
        serverPanel.add(serverPortLabel);
        serverPanel.add(serverStartButton);


        /* Tabbed Pane */
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Client", clientPanel);
        tabbedPane.add("Server", serverPanel);

        getContentPane().add(tabbedPane);
    }

    private JLabel doLabel(String title){
        JLabel label = new JLabel(title);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }


    private class StartButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            boolean ipValid = new IPValidator().validate(ipTextField.getText());
            boolean portValid = new PortValidator().validate(portTextField.getText());
            boolean nickValid = new NickValidator().validate(nickTextField.getText());
            if (!ipValid){
                JOptionPane.showMessageDialog(null, "Wrong IP address.", "Input error", JOptionPane.ERROR_MESSAGE);
            } else if (!portValid){
                JOptionPane.showMessageDialog(null, "Wrong PORT.", "Input error", JOptionPane.ERROR_MESSAGE);
            } else if (!nickValid){
                JOptionPane.showMessageDialog(null, "Wrong nick name", "Input error", JOptionPane.ERROR_MESSAGE);
            } else{
                ChatPacket chatPacket = new ChatPacket(ipTextField.getText(), portTextField.getText(), nickTextField.getText());
                setVisible(false);
                new ChatFrame("Chat", chatPacket);
            }
        }
    }

    private class StartServerButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            serverTextLabel.setText("The server has been started.");
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
