package ua.artcode.week7.proxy;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestIMath {

    private IMath math;

    @Before
    public void setUp(){
        math = new ProxyMath(new IMathImpl());
    }

    @After
    public void tearDown(){
        math = null;
    }

    @Test
    public void testSumZeroArg(){
        double res = math.sum(0,0);
        Assert.assertEquals(0, res, 0);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero(){
        double res = math.divide(56, 0);
    }


}
