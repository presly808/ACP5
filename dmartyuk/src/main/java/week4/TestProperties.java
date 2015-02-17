package week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by dmartyuk on 09.02.2015.
 */
public class TestProperties {

//    public static final String PATH = "dmartyuk/tmp/user.properties";
    public static final String PATH = "dmartyuk/tmp/user.xml";

    public static void main(String[] args) throws IOException {
//        storeProperties();
        loadProperties();
    }

    private static void loadProperties() throws IOException {
        Properties properties = new Properties();
//        properties.load(new FileInputStream(PATH));
        properties.loadFromXML(new FileInputStream(PATH));
        for(Object prop : properties.keySet()){
            System.out.println(prop + "=" + properties.getProperty(prop.toString()));
        }
        properties.list(System.out);
    }

    private static void storeProperties() throws IOException {
        Properties properties = new Properties();
        properties.setProperty("user.id", "1");
        properties.setProperty("user.age", "23");
        properties.setProperty("user.name", "Dima");

//        properties.store(new FileOutputStream(PATH), "User info");
        properties.storeToXML(new FileOutputStream(PATH), "User info");
    }
}
