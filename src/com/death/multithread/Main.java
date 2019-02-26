package com.death.multithread;

import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

    public static void main(String[] args) {

        AtomicBoolean processing = new AtomicBoolean(true);

        new TaskRunner.Builder().add(()->{
            System.out.println("Task 1");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Task 1 Completed");
        }).add(()->{
            System.out.println("Task 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 2 Completed");
        }).add(()->{
            System.out.println("Task 3");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 3 Completed");
        }).add(()->{
            System.out.println("Task 4");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 4 Completed");
        }).add(()->{
            System.out.println("Task 5");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 5 Completed");
        }).add(()->{
            System.out.println("Task 6");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 6 Completed");
        }).add(()->{
            System.out.println("Task 7");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 7 Completed");
        }).add(()->{
            System.out.println("Task 8");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 8 Completed");
        }).add(()->{
            System.out.println("Task 9");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 9 Completed");
        }).add(()->{
            System.out.println("Task 10");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Task 10 Completed");
        }).callback(new TaskRunner.Callback() {
            @Override
            public void onComplete() {
                processing.set(false);
                System.out.println("Task completed");
            }
        }).build().execute();

        while (processing.get()){

        }

        System.out.println("Program Terminated");
    }
}
