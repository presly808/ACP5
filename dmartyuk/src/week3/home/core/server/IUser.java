package week3.home.core.server;

import week3.home.core.utils.Message;

/**
 * Created by dmartyuk on 07.02.2015.
 */
public interface IUser {

    void sendMessageToClient(Message message);
}
