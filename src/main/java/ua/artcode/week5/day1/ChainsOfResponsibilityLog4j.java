package ua.artcode.week5.day1;

import org.apache.log4j.*;

/**
 * Created by serhii on 17.02.15.
 */
public class ChainsOfResponsibilityLog4j {

    public static void main(String[] args) {
        MyLayout layout = new MyLayout();
        Appender consoleAppender = new ConsoleAppender(layout);

        Logger root = LogManager.getLogger("ua.artcode");
        root.setLevel(Level.ERROR);

        root.addAppender(consoleAppender);

        Logger child1 = LogManager.getLogger("ua.artcode.server");
        child1.setLevel(Level.ERROR);
        child1.addAppender(consoleAppender);

        Logger child1_1 = Logger.getLogger("ua.artcode.server.view");
        child1_1.setAdditivity(true);
        child1_1.setLevel(Level.DEBUG);
        child1_1.addAppender(consoleAppender);

        child1_1.error("view info");


    }


}
