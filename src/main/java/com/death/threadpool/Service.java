package com.death.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Service {

    // as per the task we have to create 5 thread in the system
    private static ExecutorService executorService = Executors.newFixedThreadPool(5);


    // Shutdown the executor service
    public void stop(){
        executorService.shutdown();
    }

    public void calcuateComplexSum(Callback callback){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<Integer> sumList = new ArrayList<>();
                Future<Integer> sum1 = executorService.submit(getSum(23, 56));
                Future<Integer> sum2 = executorService.submit(getSum(78, 56));
                Future<Integer> sum3 = executorService.submit(getSum(100, 56));
                Future<Integer> sum4 = executorService.submit(getSum(217923, 56));
                Future<Integer> sum5 = executorService.submit(getSum(1233, 56));
                Future<Integer> sum6 = executorService.submit(getSum(22343, 56));
                Future<Integer> sum7 = executorService.submit(getSum(234533, 56));
                Future<Integer> sum8 = executorService.submit(getSum(2113, 56));
                Future<Integer> sum9 = executorService.submit(getSum(21463, 56));
                Future<Integer> sum10 = executorService.submit(getSum(211153, 56));

                try {
                    sumList.add(sum1.get());
                    sumList.add(sum2.get());
                    sumList.add(sum3.get());
                    sumList.add(sum4.get());
                    sumList.add(sum5.get());
                    sumList.add(sum6.get());
                    sumList.add(sum7.get());
                    sumList.add(sum8.get());
                    sumList.add(sum9.get());
                    sumList.add(sum10.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                callback.onResult(sumList);
            }
        });
    }

    private Callable<Integer> getSum(int num, int num2){
        return ()->{
          System.out.println("Calculating sum");
          Thread.sleep(2000);
          return num+num2;
        };
    }
}
