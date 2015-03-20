package ua.artcode.week8.di.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.artcode.week8.ioc.ViewA;

@Component
public class B {

    @Autowired
    private ViewA viewA;

    @Autowired
    private C c;

    public B() {
    }

    public ViewA getViewA() {
        return viewA;
    }

    public void setViewA(ViewA viewA) {
        this.viewA = viewA;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }
}
