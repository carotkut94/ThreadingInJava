package com.death.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;

public class TaskRunner extends Thread {

    public interface Callback {
        void onComplete();
    }

    private Callback callback;
    private ConcurrentLinkedQueue<Worker> workers;
    private CountDownLatch latch;

    public TaskRunner(List<Runnable> tasks, Callback callback) {
        workers = new ConcurrentLinkedQueue<>();
        latch = new CountDownLatch(tasks.size());
        this.callback = callback;

        for (Runnable singleTask : tasks) {
            workers.add(new Worker(singleTask, latch));
        }
    }

    public void execute() {
        start();
    }


    @Override
    public void run() {
        while (true) {

            Worker singleWorker = workers.poll();
            if (singleWorker == null) {
                break;
            }
            singleWorker.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(callback!=null){
            callback.onComplete();
        }
    }

    public static class Builder {
        private List<Runnable> tasks = new ArrayList<>();
        private Callback callback;

        public Builder add(Runnable task) {
            tasks.add(task);
            return this;
        }

        public Builder callback(Callback callback) {
            this.callback = callback;
            return this;
        }

        public TaskRunner build() {
            return new TaskRunner(tasks, callback);
        }
    }

}
