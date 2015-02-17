package ua.artcode.week4.day1.prop;

import java.io.*;
import java.util.Properties;

/**
 * Created by serhii on 09.02.15.
 */
public class TestProperties {

    public static final String PATH = "../ACP5/temp/user.xml";

    public static void main(String[] args) throws IOException {


        storeProperties();
        //showAllProp(PATH);
    }

    private static void storeProperties() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("user.id", "1");
        properties.setProperty("user.age", "23");
        properties.setProperty("user.name", "Kolia");
        properties.storeToXML(new FileOutputStream(PATH), "User info");
    }

    private static void showAllProp(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileReader(path));
        properties.list(System.out);
    }

}
