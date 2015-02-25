package hw4.task3;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Downloader {

    public static void load(URL address, String path) throws IOException {
        Scanner scanner = new Scanner(address.openStream());
        String line = null;
        URL link = null;
        Map<URL, String> files = new HashMap<URL, String>();

        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.contains("<a href='/get")){
                String[] tokens = line.split("'");

                for (int i = 0; i < tokens.length; i++) {
                    if(tokens[i].contains("/get")){
                        link = new URL("http://www.ex.ua" + tokens[i]);
                    }
                }

                tokens = line.split("</a>");

                for (int i = 0; i < tokens.length - 1; i++) {
                    line += tokens[i];
                }

                tokens = line.split(">");
                String fileName = tokens[tokens.length - 1];
                files.put(link, fileName);
            }
        }

        Iterator<Map.Entry<URL,String>> iterator = files.entrySet().iterator();
        FileOutputStream outputStream = null;
        InputStream inputStream = null;
        int count = 1;

        while(iterator.hasNext()){
            Map.Entry<URL,String> element = iterator.next();
            outputStream = new FileOutputStream(path + "/" + element.getValue());
            inputStream = element.getKey().openStream();
            byte[] buff = new byte[1];//???????
            int length;

            do{
                length = inputStream.read(buff);
                outputStream.write(buff);
            }while(length != -1);

            inputStream.close();
            outputStream.close();
            System.out.println(count);
            count++;
        }
    }
}