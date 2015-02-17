package ua.artcode.week4.day1.concur;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by serhii on 10.02.15.
 */
public class MakeCollectionSynchTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        List synchList = Collections.synchronizedList(list);

    }
}
