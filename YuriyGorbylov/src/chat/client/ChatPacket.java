package chat.client;

import javax.swing.*;
import java.io.Serializable;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */
public class ChatPacket implements Serializable{

    private String ip;
    private String port;
    private String nick;
    private JTextArea outputTextArea;
    private JTextArea inputTextArea;

    public ChatPacket(String ip, String port, String nick) {
        this.ip = ip;
        this.port = port;
        this.nick = nick;
    }

    public JTextArea getOutputTextArea() {
        return outputTextArea;
    }

    public void setOutputTextArea(JTextArea outputTextArea) {
        this.outputTextArea = outputTextArea;
    }

    public JTextArea getInputTextArea() {
        return inputTextArea;
    }

    public void setInputTextArea(JTextArea inputTextArea) {
        this.inputTextArea = inputTextArea;
    }

    public String getIp() {
        return ip;
    }

    public String getPort() {
        return port;
    }

    public String getNick() {
        return nick;
    }
}
