package ua.artcode.week8.rmi.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by serhii on 17.03.15.
 */
public class RunServer {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("rmi/server-context.xml");
    }
}
