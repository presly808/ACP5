package ua.artcode.week7.proxy;

/**
 * Created by serhii on 11.03.15.
 */
public class IMathImpl implements IMath {
    @Override
    public double sum(double d1, double d2) {
        return d1 + d2;
    }

    @Override
    public double divide(double d1, double d2) {
        return d1 / d2;
    }

    @Override
    public double multiply(double d1, double d2) {
        return d1 * d2;
    }
}
