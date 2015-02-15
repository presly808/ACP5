package ua.artcode.week2.day1;

import java.io.*;
import java.util.Scanner;

/**
 * Created by serhii on 26.01.15.
 */
public class IOUtils {


    public static String readTest(String path, int buffSize){
        byte[] buff = new byte[buffSize];
        StringBuilder sb = new StringBuilder();
        try {
            InputStream is = new FileInputStream(path);
            int size = 0;
            while((size = is.read(buff)) != -1){
                String part = new String(buff, 0, size);
                sb.append(part);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static void writeTo(String path, String data){
        //try with resources
        try (OutputStream os = new FileOutputStream(path)){
            //os.write(data.getBytes());
            byte[] buff = data.getBytes();
            for (int i = 0; i < buff.length; i++) {
                os.write(buff[i]);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void testAdapter(InputStream inputStream){

        Reader adapter = new InputStreamReader(inputStream);

        BufferedReader bf = new BufferedReader(adapter);

    }

    public static void testDecorator() throws IOException {
        ObjectOutputStream oos =
                new ObjectOutputStream(
                        new FileOutputStream("path"));


    }

    public static void showChildren(String dirPath){
        File dir = new File(dirPath);// file or folder

        for(String child : dir.list()){
            System.out.println(child);
        }

    }


    public static void writeToWriter(String path, String data){
        //try with resources
        try (Writer os = new FileWriter(path)){
            //os.write(data.getBytes());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void pipeTest(String path){
        // in
        try {

            // one thread
            PipedOutputStream pos = new PipedOutputStream();
            PrintWriter pw = new PrintWriter(pos);


            // write -> pipeOut -> pipeIn -> read

            // another thread
            Scanner sc = new Scanner(new PipedInputStream(pos));
            // read from stream connected to

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
