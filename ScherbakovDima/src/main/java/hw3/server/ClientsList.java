package hw3.server;

import hw3.Message;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ClientsList {
    private List<OutputStream> clients;

    public ClientsList(){
        clients = new ArrayList<OutputStream>(5);
    }

    public ClientsList(List<OutputStream> clients){
        this.clients = clients;
    }

    public void addClient(OutputStream stream){
        clients.add(stream);
    }

    public List<OutputStream> getClients() {
        return clients;
    }

    public void setClients(List<OutputStream> clients) {
        this.clients = clients;
    }

    public void send(Message message){
        ObjectOutputStream oos = null;
        for(OutputStream stream : clients){
            try {
                oos = new ObjectOutputStream(stream);
                oos.writeObject(message);
                oos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}