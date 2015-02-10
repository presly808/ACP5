package week3.home.core.utils;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by dmartyuk on 07.02.2015.
 */
public class Message implements Serializable{

    private String message;
    private String userName;
    private Date sendTime;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
