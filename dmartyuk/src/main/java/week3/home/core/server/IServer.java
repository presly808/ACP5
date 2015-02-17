package week3.home.core.server;

import week3.home.core.utils.Message;

/**
 * Created by dmartyuk on 07.02.2015.
 */
public interface IServer {

    void addNewUser(IUser user);

    void deleteUser(IUser user);

    void notifyUsers(Message message);
}
