package ua.artcode.week8.ioc;

import java.util.Date;

/**
 * Created by serhii on 16.03.15.
 */
public class ServiceB implements IService {
    @Override
    public String format(Date date) {
        return String.format("%1$ty-%1$tm-%1$td", date);
    }
}
