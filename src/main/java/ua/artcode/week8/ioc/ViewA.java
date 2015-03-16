package ua.artcode.week8.ioc;

import java.util.Date;

/**
 * Created by serhii on 16.03.15.
 */
public class ViewA {
    // must choose realization
    private IService service;

    public void useService(){
        System.out.println(service.format(new Date()));
    }


}
