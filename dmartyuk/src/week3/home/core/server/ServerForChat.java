package week3.home.core.server;

import week3.home.core.utils.Message;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dmartyuk on 07.02.2015.
 */
public class ServerForChat implements IServer {

    private final static int PORT = 7941;
    private static Set<IUser> usersConnectionPull = new HashSet<IUser>();

    public static void main(String[] args) throws IOException {
        ServerForChat serverForChat = new ServerForChat();
        ServerSocket server = new ServerSocket(PORT);

        while(true) {
            Socket userConnection = server.accept();
            Date now = new Date();
            String userIP = userConnection.getInetAddress().toString();
            System.out.printf("%td.%tm.%ty connection ip address %s %n", now, now, now, userIP);
            WorkerUser workerUser = new WorkerUser(userConnection, serverForChat, userIP);
            serverForChat.addNewUser(workerUser);
            new Thread(workerUser).start();
        }
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
