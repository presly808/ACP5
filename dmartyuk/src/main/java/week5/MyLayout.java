package week5;

import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;

import java.util.Date;

/**
 * Created by dmartyuk on 16.02.2015.
 */
public class MyLayout extends Layout {
    @Override
    public String format(LoggingEvent event) {
        Date date = new Date(event.getTimeStamp());

        return String.format("[%1$tY/%1$tm/%1$td %1$tH:%1$tM:%1$tL] %6$s %2$s[line:%3$s] [%4$s] [%5$s]",
                date,
                event.getLocationInformation().getMethodName(),
                event.getLocationInformation().getLineNumber(),
                event.getLevel(),
                event.getMessage(),
                event.getLoggerName());
    }

    @Override
    public boolean ignoresThrowable() {
        return false;
    }

    @Override
    public void activateOptions() {

    }
}
