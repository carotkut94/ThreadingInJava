package com.death.multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Worker implements Runnable {

    private Thread workerThread;
    private Runnable task;
    private AtomicBoolean flag;
    private CountDownLatch latch;
    public Worker(Runnable runnable, CountDownLatch latch){
        workerThread = new Thread(this);
        flag = new AtomicBoolean(false);
        task = runnable;
        this.latch = latch;
    }

    /**
     * It should not be started multiple times, by other threads
     * once it is started
     */
    public void start(){
        if(!flag.getAndSet(true)) {
            workerThread.start();
        }
    }

    @Override
    public void run() {
        task.run();
        latch.countDown();
    }
}
