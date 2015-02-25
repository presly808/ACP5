package hw3;

import hw3.anotations.SerializableClass;
import hw3.anotations.SerializableField;

import java.util.List;

@SerializableClass
public class User {

    @SerializableField(name = "id")
    private int id;
    @SerializableField(name = "ip")
    private String ip;
    @SerializableField(name = "port")
    private int port;
    @SerializableField(name = "name")
    private String name;
    @SerializableField(name = "password")
    private String password;

    public User(){}

    public User(String ip, int port, String name, String password) {
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", port=" + port +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setName(String name) {
        this.name = name;
    }
}