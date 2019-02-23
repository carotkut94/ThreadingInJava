package com.death;

/**
 * Thread creation by implementing Runnable Interface
 * in this case we will have to implement the run method
 *
 * Here concept of child and main thread is
 */

class NewThreadCreator implements Runnable {

    Thread t;

    NewThreadCreator(){
        t = new Thread(this, "Child Thread");
        System.out.println("Child "+t);
        t.start(); // -> this will  call the overriden run() method and will execute the code in it.
    }

    // Override method from runnable interface
    @Override
    public void run() {
        // We need to put this code in try - catch because, thread might get interrupted
        // by other thread with higher priority
        try{
            for (int i=5; i>0; i--){
                System.out.println("Child Thread "+i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Exiting Child Thread");
    }
}


/**
 * Driver program for creating child thread
 */
public class NewThread {
    public static void main(String... args){
         new NewThreadCreator();
         try{
             for(int i=5;i>0;i--){
                 System.out.println("Main Thread "+i);
                 Thread.sleep(1000);
             }
         }catch (InterruptedException e){
             e.printStackTrace();
         }
         System.out.println("Main Thread Exiting");
    }
}
