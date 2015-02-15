package week4.day1;

import java.io.*;
import java.util.Properties;

/**
 * Created by Yuriy on 09.02.2015.
 */
public class TestProperties {

    public static final String PATH = "D:\\info.xml";

    public static void main(String[] args) throws IOException {
        storeProperties();
        //loadProperties();

    }

    private static void storeProperties() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("user.id", "1");
        properties.setProperty("user.age", "23");
        properties.setProperty("user.name", "Kolya");
        properties.storeToXML(new FileOutputStream(PATH), "User info");
    }
    private static void loadProperties() throws IOException {
        Properties properties2 = new Properties();
        properties2.load(new FileInputStream(PATH));
        System.out.println(properties2.getProperty("user.name"));
        System.out.println(properties2.getProperty("user.id"));
        System.out.println(properties2.getProperty("user.age"));
        properties2.list(System.out);


    }
}
