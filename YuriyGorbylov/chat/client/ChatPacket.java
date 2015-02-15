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
    private String message;

    public ChatPacket(String ip, String port, String nick, String message) {
        this.ip = ip;
        this.port = port;
        this.nick = nick;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
