package com.death.threadpool;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String... args){
        System.out.println("Program Started");
        // will be used to keep the Main thread alive
        AtomicBoolean processing = new AtomicBoolean(true);

        Service service = new Service();
        service.calcuateComplexSum(calculations -> {
            for(Integer singleSum: calculations){
                System.out.println(singleSum);
            }
            processing.set(false);
        });

        while (processing.get()){}
        service.stop();
        System.out.println("Program Terminated");
    }
}
