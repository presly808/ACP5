package week4.day1.concur;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Yuriy on 10.02.2015.
 */
public class MakeColletionSynchTest {

    public static void main(String[] args) {

        List<Integer> l = Arrays.asList(1, 2, 3, 4, 5);
        List synchList = Collections.synchronizedList(l);


    }
}
