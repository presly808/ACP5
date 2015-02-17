package ua.artcode.week5.day1;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Date;

/**
 * Created by serhii on 16.02.15.
 */
public class MyLayout extends Layout {
    @Override
    public String format(LoggingEvent event) {
        Date date = new Date(event.getTimeStamp());
        String formatted = String.format(
                "[%1$ty/%1$tM/%1$td %1$tH:%1$tm:%1$tS] %4$s %2$s %3$s\n",
                date,
                event.getLevel(),
                event.getMessage(),
                event.getLoggerName());
        return formatted;
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }

    @Override
    public void activateOptions() {

    }
}
