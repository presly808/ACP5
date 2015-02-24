package ua.artcode.manager;

/**
 * Created by serhii on 24.02.15.
 */
public interface ClientManager {

    public void register(String login, String pass,
                         String phone, String email);


    public String signIn(String login, String pass);

    public void logout(String accessKey);



}
