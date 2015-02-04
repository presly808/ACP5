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

    private JTextField ipTextField;
    private JTextField portTextField;
    private JTextField nickTextField;
    private JRadioButton clientRadioButton;
    private JRadioButton serverRadioButton;
    private JButton startButton;

    public LoginFrame(String title) throws HeadlessException {
        super(title);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(400, 200);
        setResizable(false);
        init();

    }

    private void init(){

        /* RadioButton panel */

        clientRadioButton = new JRadioButton("Client", true);
        clientRadioButton.addItemListener(new ClientRadioButtonListener());     // Listeners for radio buttons
        serverRadioButton = new JRadioButton("Server", false);
        serverRadioButton.addItemListener(new ServerRadioButtonListener());

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(clientRadioButton);
        buttonGroup.add(serverRadioButton);

        JPanel radioButtonPanel = new JPanel(new GridLayout(1, 4));
        radioButtonPanel.setBorder(new TitledBorder("Mode"));
        radioButtonPanel.add(new Label());                          // Labels is used for alignment
        radioButtonPanel.add(clientRadioButton);
        radioButtonPanel.add(serverRadioButton);
        radioButtonPanel.add(new Label());


        /* Text fields panel */
        GridLayout clientPanelGridLayout = new GridLayout(2,3);
        clientPanelGridLayout.setHgap(5);
        clientPanelGridLayout.setVgap(5);
        JPanel clientPanel = new JPanel(clientPanelGridLayout);
        clientPanel.setBorder(new TitledBorder("IP/PORT"));

        JLabel ipLabel = doLabel("IP");
        JLabel portLabel = doLabel("PORT");
        JLabel nickLabel = doLabel("Nick");

        clientPanel.add(ipLabel);
        clientPanel.add(portLabel);
        clientPanel.add(nickLabel);

        ipTextField = new JTextField();
        portTextField = new JTextField();
        nickTextField = new JTextField();
        clientPanel.add(ipTextField);
        clientPanel.add(portTextField);
        clientPanel.add(nickTextField);


        /* Button Panel */
        startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonActionListener());
        JPanel startPanel = new JPanel(new GridLayout(1,3));
        startPanel.add(new Label());
        startPanel.add(startButton);
        startPanel.add(new Label());

        /* Main Panel */
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.add(radioButtonPanel, BorderLayout.NORTH);
        mainPanel.add(clientPanel, BorderLayout.CENTER);
        mainPanel.add(startPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
    }

    private JLabel doLabel(String title){
        JLabel label = new JLabel(title);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    private class ServerRadioButtonListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Color myGray = new Color(240, 240, 240);
                ipTextField.setEnabled(false);
                ipTextField.setBackground(myGray);
                portTextField.setEnabled(false);
                portTextField.setBackground(myGray);
                nickTextField.setEnabled(false);
                nickTextField.setBackground(myGray);
            }
        }
    }

    private class ClientRadioButtonListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                ipTextField.setEnabled(true);
                ipTextField.setBackground(Color.WHITE);
                portTextField.setEnabled(true);
                portTextField.setBackground(Color.WHITE);
                nickTextField.setEnabled(true);
                nickTextField.setBackground(Color.WHITE);
            }
        }
    }

    private class StartButtonActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if (serverRadioButton.isSelected()){
                setTitle("Server has been started");
                ChatServer server = new ChatServer();
                try {
                    server.start();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                startButton.setText("Stop");
            } else if (clientRadioButton.isSelected()){
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
    }
}
