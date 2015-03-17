package ua.artcode.week8.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.artcode.week8.ioc.ViewA;

/**
 * Created by serhii on 17.03.15.
 */
public class InitWithReferences {

    public static void main(String[] args) {
        ApplicationContext app =
                new ClassPathXmlApplicationContext("classpath:week8/app-context.xml");
        ViewA viewA = app.getBean(ViewA.class);

        viewA.useService();
    }
}
