package ua.artcode.manager;

import ua.artcode.exception.NoUserFoundException;

/**
 * Created by serhii on 24.02.15.
 */
public interface ClientManager {

    public void register(String login, String pass,
                         String phone, String email);


    public String signIn(String login, String pass) throws NoUserFoundException;

    public void logout(String accessKey);



}
