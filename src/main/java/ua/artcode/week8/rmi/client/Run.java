package ua.artcode.week8.rmi.client;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.artcode.week8.rmi.common.ServerAPI;

/**
 * Created by serhii on 17.03.15.
 */
public class Run {

    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("rmi/client-context.xml");

        ServerAPI serverAPI = app.getBean(ServerAPI.class);

        System.out.println(serverAPI.getDate());
        System.out.println(serverAPI.sayHello("Vania"));

    }


}
