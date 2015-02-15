package ua.artcode.week2.refl_serial;

import java.io.FileNotFoundException;

/**
 * Created by serhii on 27.01.15.
 */
public class TestPOstProcess {

    public static void main(String[] args) throws FileNotFoundException, IllegalAccessException {
        Robot robot = new Robot(1,2000,"AR-25", 1234, 10000);
        AnnotationPostProcess<Robot> postProcess = new AnnotationPostProcess<>();
        postProcess.save(robot);
    }

}
