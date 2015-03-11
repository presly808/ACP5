package ua.artcode.week7.proxy;

/**
 * Created by serhii on 11.03.15.
 */
public class ProxyMath implements IMath {

    private IMath target;

    public ProxyMath(IMath target) {
        this.target = target;
    }

    @Override
    public double sum(double d1, double d2) {
        return 0;
    }

    @Override
    public double divide(double d1, double d2) {
        if(d2 == 0){
            System.err.println("can not divide by zeros");
            throw new ArithmeticException("can not divide by zeros");
        }


        return target.divide(d1, d2);
    }

    @Override
    public double multiply(double d1, double d2) {
        return d1 == 0 || d2 == 0 ? 0 : target.multiply(d1, d2);
    }
}
