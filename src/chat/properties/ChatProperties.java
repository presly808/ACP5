package chat.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by yuriy.gorbylev on 10.02.2015.
 */
public class ChatProperties {

    private static final String PATH = "YuriyGorbylov\\src\\chat\\properties\\info.properties";


    public static void storeProperties(String ip, String port, String nick){
        Properties properties = new Properties();
        properties.setProperty("server.ip", ip);
        properties.setProperty("server.port", port);
        properties.setProperty("user.nick", nick);
        try (FileOutputStream fos = new FileOutputStream(PATH)){
            properties.store(fos, "User info");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String loadIP() {
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream(PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("server.ip");
    }

    public static String loadPort() {
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream(PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("server.port");
    }

    public static String loadNick() {
        Properties properties = new Properties();
        try(FileInputStream fis = new FileInputStream(PATH)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty("user.nick");
    }
}
