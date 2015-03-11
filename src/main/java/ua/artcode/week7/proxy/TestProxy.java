package ua.artcode.week7.proxy;

/**
 * Created by serhii on 11.03.15.
 */
public class TestProxy {

    public static void main(String[] args) {
        IMath target = new IMathImpl();

        IMath proxy = new ProxyMath(target);


        MathClient client = new MathClient(proxy);

        client.testIMath(12,0);

    }
}
