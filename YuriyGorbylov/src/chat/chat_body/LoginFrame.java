package chat.chat_body;

import chat.client.ChatPacket;
import chat.properties.ChatProperties;
import chat.validators.IPValidator;
import chat.validators.NickValidator;
import chat.validators.PortValidator;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */
public class LoginFrame extends JFrame{

    private final Font FONT = new Font("Calibri", Font.BOLD, 20);

    private JTextField ipTextField;
    private JTextField portTextField;
    private JTextField nickTextField;
    private JTextField serverPortTextField;
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

        ipTextField = new JTextField(ChatProperties.loadIP());
        ipTextField.setHorizontalAlignment(SwingConstants.CENTER);
        portTextField = new JTextField(ChatProperties.loadPort());
        portTextField.setHorizontalAlignment(SwingConstants.CENTER);
        nickTextField = new JTextField(ChatProperties.loadNick());
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

        serverPortTextField = new JTextField("Input server's port");
        serverPortTextField.setFont(new Font("Calibri", Font.ITALIC, 14));
        serverPortTextField.setDisabledTextColor(Color.LIGHT_GRAY);
        serverPortTextField.setPreferredSize(new Dimension(100, 10));
        serverPortTextField.setHorizontalAlignment(SwingConstants.CENTER);
        serverPortTextField.addMouseListener(new PortTextFieldMouseListener());

        JPanel portTextFieldPanel = new JPanel(new GridLayout(1,3));
        portTextFieldPanel.add(new Label());
        portTextFieldPanel.add(serverPortTextField);
        portTextFieldPanel.add(new Label());


        JButton serverStartButton = new JButton("Start");
        serverStartButton.addActionListener(new StartServerButtonActionListener());
        JPanel startButtonPanel = new JPanel();
        startButtonPanel.add(serverStartButton);

        JPanel serverPanel = new JPanel(new GridLayout(3,1,5,5));
        serverPanel.add(serverTextLabel);
        serverPanel.add(portTextFieldPanel);
        serverPanel.add(startButtonPanel);


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
                ChatProperties.storeProperties(ipTextField.getText(), portTextField.getText(), nickTextField.getText());
                ChatPacket chatPacket = new ChatPacket(ipTextField.getText(), portTextField.getText(), nickTextField.getText(), null);
                setVisible(false);
                new ChatFrame("Chat", chatPacket);
            }
        }
    }

    private class StartServerButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            setVisible(false);
            new ServerFrame("Chat", Integer.valueOf(portTextField.getText()));
        }
    }

    private class PortTextFieldMouseListener extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e) {
            serverPortTextField.setText("");
        }
    }
}
