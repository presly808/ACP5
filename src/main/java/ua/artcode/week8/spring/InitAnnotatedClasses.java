package ua.artcode.week8.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.artcode.week8.di.spring.A;

/**
 * Created by serhii on 17.03.15.
 */
public class InitAnnotatedClasses {

    public static void main(String[] args) {
        ApplicationContext app =
                new ClassPathXmlApplicationContext("week8/app-context.xml");
        A a = app.getBean("a",A.class);
        a.getB().getC();
    }
}
