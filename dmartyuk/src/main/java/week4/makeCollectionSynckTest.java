package week4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by dmartyuk on 10.02.2015.
 */
public class makeCollectionSynckTest {

    public static void main(String[] args) {
        List list = Arrays.asList(1,2,3,4,5);
        List synckList = Collections.synchronizedList(list);

    }
}
