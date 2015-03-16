package ua.artcode.week8.ioc;

import java.util.Date;

/**
 * Created by serhii on 16.03.15.
 */
public class ServiceA implements IService {
    @Override
    public String format(Date date) {
        return date.toString();
    }
}
