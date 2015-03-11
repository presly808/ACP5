package ua.artcode.week7.proxy;

/**
 * Created by serhii on 11.03.15.
 */
public class MathClient {

    private IMath math;

    public MathClient(IMath math) {
        this.math = math;
    }

    public void testIMath(double d1, double d2){
        math.sum(d1,d2);
        math.multiply(d1,d2);
        math.divide(d1,d2);
    }

}
