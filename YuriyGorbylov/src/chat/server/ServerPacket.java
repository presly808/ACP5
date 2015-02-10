package chat.server;

import java.util.List;

/**
 * Created by yuriy.gorbylev on 10.02.2015.
 */
public class ServerPacket {

    private String message;
    private List<String> list;

    public ServerPacket(String message, List<String> list) {
        this.message = message;
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getList() {
        return list;
    }
}
