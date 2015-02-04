package chat.client;

/**
 * Created by yuriy.gorbylev on 04.02.2015.
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ReadRunner implements Runnable {

    private Socket serverSocket;
    private Scanner in;

    public ReadRunner(Socket socket) {
        this.serverSocket = socket;
        try {
            in = new Scanner(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (in.hasNextLine()) {
            System.out.println(in.nextLine());
        }
    }
}

