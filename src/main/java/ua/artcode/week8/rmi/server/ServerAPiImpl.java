package ua.artcode.week8.rmi.server;

import ua.artcode.week8.rmi.common.ServerAPI;

import java.util.Date;

/**
 * Created by serhii on 17.03.15.
 */
public class ServerAPiImpl implements ServerAPI {

    @Override
    public String getDate() {
        return String.format("%1$tH-%1$tM-%1$tS", new Date());
    }

    @Override
    public String sayHello(String name) {
        return "Hello from server " + name;
    }
}
