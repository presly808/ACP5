package ua.artcode.week8.ioc;

/**
 * Created by serhii on 16.03.15.
 */
public class TestDInjector {


    public static void main(String[] args) {
        DependencyInjector dependencyInjector =
                new DependencyInjector();

        ViewA viewA = new ViewA();


        dependencyInjector.doInjection(viewA);


        viewA.useService();
    }
}
