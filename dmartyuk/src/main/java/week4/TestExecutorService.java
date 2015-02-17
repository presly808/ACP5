package week4;

import java.util.concurrent.*;

/**
 * Created by dmartyuk on 09.02.2015.
 */
public class TestExecutorService {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<Integer> future = executorService.submit(new SumTask());
        while(!future.isDone()){

        }
        Integer res = future.get();
        System.out.println(res);
        executorService.shutdown();
    }
}


class SumTask implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int[] mas = {1,2,3,4,5,5,6,7,89,34};
        int res = 0;
        for(int i : mas){
            res +=i;
        }
        return res;
    }
}
