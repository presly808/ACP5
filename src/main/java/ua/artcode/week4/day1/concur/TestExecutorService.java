package ua.artcode.week4.day1.concur;

import java.util.concurrent.*;

/**
 * Created by serhii on 09.02.15.
 */
public class TestExecutorService {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);
        Future<Integer> future = service.submit(new SumTask());
        while(!future.isDone()){

        }
        Integer res = future.get();
        System.out.println(res);
        service.shutdown();
    }


}

class SumTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int[] mas = {1,2,3,4,5,5,6,7,89,34};
        int res = 0;
        for(int i : mas){
            res += i;
        }
        return res;
    }
}

