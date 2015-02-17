package week3.home.core.server;

import week3.home.core.utils.Message;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * Created by dmartyuk on 07.02.2015.
 */
public class ServerForChat implements IServer {
    private final static String PROPERTI_PORT = "PORT";
    private static int PORT;
    private static Set<IUser> usersConnectionPull = new HashSet<IUser>();

    public static void main(String[] args) throws IOException {
        serverInit();
        ServerForChat serverForChat = new ServerForChat();
        ServerSocket server = new ServerSocket(PORT);
        Date startTime = new Date();
        System.out.println("SERVER START TIME " + startTime);
        while(true) {
            System.out.println("Wait user connection...");
            Socket userConnection = server.accept();
            Date now = new Date();
            String userIP = userConnection.getInetAddress().toString();
            System.out.printf("%td.%tm.%ty connection ip address %s %n", now, now, now, userIP);
            WorkerUser workerUser = new WorkerUser(userConnection, serverForChat, userIP);
            serverForChat.addNewUser(workerUser);
            new Thread(workerUser).start();
        }
    }

    private static void serverInit() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("dmartyuk/week3/home/core/server/server.properties"));
        PORT = Integer.valueOf(properties.getProperty(PROPERTI_PORT));
    }

    @Override
    public void addNewUser(IUser user) {
        usersConnectionPull.add(user);
    }

    @Override
    public void deleteUser(IUser user) {
        usersConnectionPull.remove(user);
    }

    @Override
    public void notifyUsers(Message message) {
        for(IUser user : usersConnectionPull){
            user.sendMessageToClient(message);
        }
    }
}
