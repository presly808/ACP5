package ua.artcode.week8.di.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.artcode.week8.ioc.IService;

@Component("myCBean")
public class C {

    @Autowired
    @Qualifier("serviceB")
    private IService service;

    public C() {
    }

    public C(IService service) {
        this.service = service;
    }

    public IService getService() {
        return service;
    }

    public void setService(IService service) {
        this.service = service;
    }
}
