package ua.artcode.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by serhii on 22.03.15.
 */
public class InitSpringContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String location = sce.getServletContext().getInitParameter("springLocation");
        ApplicationContext applicationContext =
                new ClassPathXmlApplicationContext(location);
        sce.getServletContext().setAttribute("applicationContext", applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
