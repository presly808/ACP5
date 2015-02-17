package ua.artcode.week5.day1;


import org.apache.log4j.*;

import java.io.IOException;

/**
 * Created by serhii on 16.02.15.
 */
public class TestLogger {

    public static void main(String[] args) throws IOException {
        Logger logger = LogManager.getLogger(TestLogger.class);
        logger.setLevel(Level.INFO);


        Layout layout = new HTMLLayout();
        Appender consoleAppender = new ConsoleAppender(layout);
        Appender fileAppender = new FileAppender(layout, "../ACP5/temp/log.html", false);



        logger.addAppender(consoleAppender);
        logger.addAppender(fileAppender);

        logger.info("some");

        logger.removeAllAppenders();
    }

}
