package week5;

import org.apache.log4j.*;

/**
 * Created by dmartyuk on 17.02.2015.
 */
public class ChainOfResponsobilitiesLog4j {

    public static void main(String[] args) {
        MyLayout layout = new MyLayout();
        ConsoleAppender consoleAppender = new ConsoleAppender(layout);

        Logger root = LogManager.getLogger("ua.artcode");
        root.setLevel(Level.ERROR);
        root.addAppender(consoleAppender);

        Logger child1 = LogManager.getLogger("ua.artcode.server");
        child1.setLevel(Level.ERROR);
        child1.addAppender(consoleAppender);

        Logger child1_1 = LogManager.getLogger("ua.artcode.server.view");
        child1_1.setLevel(Level.INFO);
        child1_1.addAppender(consoleAppender);

        child1_1.info("view info\n");

    }
}
