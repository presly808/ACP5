package hw3;

import hw3.anotations.SerializableClass;
import hw3.anotations.SerializableField;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SerializableClass
public class Message implements Serializable{

    @SerializableField(name = "id")
    private int id;
    @SerializableField(name = "user name")
    private String userName;
    @SerializableField(name = "date")
    private Date date;
    @SerializableField(name = "message")
    private String message;

    public Message(){}

    public Message(String userName, Date date, String message) {
        this.userName = userName;
        this.date = date;
        this.message = message;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String stringDate = dateFormatter.format(date);
        return "[" + id + "]" + userName + " - " + stringDate + "\n" + message + "\n";
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}