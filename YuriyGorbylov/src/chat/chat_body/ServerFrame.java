package chat.chat_body;

import chat.server.ChatServer;
import chat.validators.NumberValidator;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Yuriy on 09.02.2015.
 */
public class ServerFrame extends JFrame{

    public final int WIDTH = 320;
    public final int HEIGHT = 425;
    private final Color BACKGROUND_COLOR = new Color(245,255,240);
    private final Color UNEDITABLE_TEXT_FIELD = new Color(230,230,230);
    private final Border BORDER = new EtchedBorder(EtchedBorder.RAISED);

    private int port;
    private boolean isStartPressed = false;

    private JTextField numberTextField;
    private JButton startButton;
    private ChatServer chatServer;
    private JList serverUserList;


    public ServerFrame(String title, int port) throws HeadlessException {
        super(title);
        this.port = port;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        init();
        Dimension screenCenter = SwingUtils.getScreenCenterSize(WIDTH, HEIGHT);
        setBounds(screenCenter.width, screenCenter.height, WIDTH, HEIGHT);
        setVisible(true);
    }

    private void init(){

        /* MENU BAR */
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        /* USER LIST */
        serverUserList = doList(280, 300);
        JLabel usersLabel = new JLabel("Connected users");
        usersLabel.setHorizontalAlignment(SwingConstants.CENTER);

        /* START BUTTON */
        startButton = new JButton("Start");
        startButton.addActionListener(new StartActionListener());

        /* TEXT FIELD */
        numberTextField = new JTextField();
        numberTextField.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel numberLabel = new JLabel("Max users: ");
        numberLabel.setHorizontalAlignment(SwingConstants.LEFT);

        /* PANELS */

        /* Center Panel */
        JPanel centerPanel = new JPanel(new BorderLayout(5,5));
        centerPanel.add(serverUserList, BorderLayout.CENTER);
        centerPanel.add(usersLabel, BorderLayout.NORTH);

        /* Number panel */
        JPanel numberPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        numberPanel.add(numberLabel);
        numberPanel.add(numberTextField);

        /* South Panel */
        JPanel southPanel = new JPanel(new GridLayout(1, 3, 5, 0));
        JPanel separatorPanel = new JPanel(new BorderLayout());
        southPanel.add(numberPanel);
        southPanel.add(startButton);

        setJMenuBar(menuBar);
        JPanel mainPanel = new JPanel();
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

    }

    private JList doList(int width, int height){
        JList list = new JList();
        list.setPreferredSize(new Dimension(width, height));
        list.setBorder(BORDER);
        list.setBackground(BACKGROUND_COLOR);
        return list;
    }

    private class StartActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            boolean numberValid = new NumberValidator().validate(numberTextField.getText());
            if (numberValid){
                isStartPressed = !isStartPressed;
                if (isStartPressed) {
                    chatServer = new ChatServer(port, Integer.valueOf(numberTextField.getText()), serverUserList);
                    numberTextField.setEditable(false);
                    numberTextField.setBackground(UNEDITABLE_TEXT_FIELD);
                    startButton.setText("Stop");
                    setTitle("Server is ON");
                    chatServer.start();
                } else {
                    startButton.setText("Start");
                    setTitle("Server is OFF");
                    numberTextField.setEditable(true);
                    numberTextField.setBackground(Color.WHITE);
                    chatServer.stopServer();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Wrong number!", "Input error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
