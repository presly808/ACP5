package ua.artcode.week2.day1;

/**
 * Created by serhii on 26.01.15.
 */
public class TestIO {


    public static final String PATH = "/home/serhii/java_error_in_IDEA_6393.log";

    public static void main(String[] args) {
        String res = IOUtils.readTest(PATH, 2048);
        System.out.println(res);

        IOUtils.writeTo("/home/serhii/new.txt", "some text 1234567");


        IOUtils.showChildren("/etc");


    }
}
