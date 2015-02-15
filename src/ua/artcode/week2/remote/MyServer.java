package ua.artcode.week2.remote;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class MyServer {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(9090);

        while(true){
            Socket clientSocket = ss.accept();
            System.out.println("*********************");
            System.out.printf("address=%s\n", clientSocket.getInetAddress().toString());
            Scanner scanner = new Scanner(clientSocket.getInputStream());
            while (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
            System.out.println("*********************");

            OutputStream os = clientSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.printf("Hello from server, date=%tc", new Date());
            pw.flush();
            pw.close();
        }
    }



}
