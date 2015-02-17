package week5;

import org.apache.log4j.*;

import java.io.IOException;

/**
 * Created by dmartyuk on 16.02.2015.
 */
public class TestLogger {

    public static final String FILENAME = "D:\\workspace\\git-first\\ACP5\\dmartyuk\\src\\week5\\logging.html";

    public static void main(String[] args) throws IOException {
        Logger logger = LogManager.getLogger(TestLogger.class);

        Layout layout = new MyLayout();
//        Appender appender = new FileAppender(layout, FILENAME,true);
        Appender appender = new ConsoleAppender(layout);
        logger.addAppender(appender);

        System.out.println(logger.getLevel());
        testInfo(logger);
    }

    private static void testInfo(Logger logger){

        logger.info("ebala");
    }
}
