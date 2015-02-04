package chat.client;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */
public class ChatPacket {

    private String ip;
    private String port;
    private String nick;

    public ChatPacket(String ip, String port, String nick) {
        this.ip = ip;
        this.port = port;
        this.nick = nick;
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
