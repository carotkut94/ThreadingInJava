package com.death;

/**
 * Here we will create the thread by extending the Thread class,
 * There are two ways for creating thread, one involves the implementation of
 * Runnable and by extending Thread class
 *
 * By Extending thread class only should be used when you need to improve something upon
 * thread class, otherwise stick to implementing the Runnable interface
 */

class NewThreadByExtension extends Thread {

    NewThreadByExtension(String name) {
        super(name);
        System.out.println(name + this);
        start();
    }

    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(currentThread().getName()+ " Thread " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(currentThread().getName()+" Thread Interrupted");
        }
        System.out.println("Exiting "+currentThread().getName()+" thread");
    }
}

public class ThreadExtensionDemo {
    public static void main(String... args) {
        /**
         * Creation  of multiple thread,
         * with name
         */
        new NewThreadByExtension("One");
        new NewThreadByExtension("Two");
        new NewThreadByExtension("Three");
        new NewThreadByExtension("Four");

        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Main Thread " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Main Thread Interrupted");
        }
        System.out.println("Exiting Main thread");

    }
}
