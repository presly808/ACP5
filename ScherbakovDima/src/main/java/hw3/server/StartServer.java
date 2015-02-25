package hw3.server;

public class StartServer {
    public static void main(String[] args) {
        ClientsList clientsList = new ClientsList();
        new Thread(new ServerOutputThread(clientsList)).start();
    }
}
