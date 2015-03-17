package ua.artcode.week8.ioc;

import java.util.Date;

/**
 * Created by serhii on 16.03.15.
 */
public class ViewA {
    // must choose realization
    @ForInject
    private IService service;

    private int count;

    private String desc;

    public ViewA() {
    }

    public ViewA(IService service) {
        this.service = service;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setService(IService service) {
        this.service = service;
    }

    public void useService(){
        System.out.println(service.format(new Date()));
    }


}
