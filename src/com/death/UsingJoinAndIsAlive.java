package com.death;

/**
 * Correct way for waiting for other thread to compete and join to the main at end
 */

class NewThreadByExtending implements Runnable {

    Thread t;
    NewThreadByExtending(String name) {
        System.out.println(name + this);
        t = new Thread(this, name);
        t.start();
    }

    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(t.getName()+ " Thread " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(t.getName()+" Thread Interrupted");
        }
        System.out.println("Exiting "+t.getName()+" thread");
    }
}

public class UsingJoinAndIsAlive {
    public static void main(String... args) {
        /**
         * Creation  of multiple thread,
         * with name
         */
        NewThreadByExtending ob1 =  new NewThreadByExtending("One");
        NewThreadByExtending ob2 =new NewThreadByExtending("Two");
        NewThreadByExtending ob3 = new NewThreadByExtending("Three");


        System.out.println("Thread one is alive: "+ob1.t.isAlive());
        System.out.println("Thread two is alive: "+ob2.t.isAlive());
        System.out.println("Thread three is alive: "+ob3.t.isAlive());


        try{
            System.out.println("Waiting for other thread to finish");
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        System.out.println("Thread one is alive: "+ob1.t.isAlive());
        System.out.println("Thread two is alive: "+ob2.t.isAlive());
        System.out.println("Thread three is alive: "+ob3.t.isAlive());

        System.out.println("Exiting Main thread");

    }
}
