package ua.artcode.week2.remote;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by serhii on 27.01.15.
 */
public class MyClientStart {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("192.168.1.40", 9090);
        Scanner scanner = new Scanner(socket.getInputStream());
        while (scanner.hasNextLine()){
            System.out.println(scanner.nextLine());
        }
    }


}
