package com.death;



class BrandNewThread implements Runnable{
    String name;
    Thread t;
    boolean suspendedFlag;


    BrandNewThread(String threadName){
        name = threadName;
        t = new Thread(this, name);
        System.out.println("New Thread: "+t);
        suspendedFlag = false;
        t.start();
    }


    @Override
    public void run() {
        try {
            for (int i = 15; i > 0; i--) {
                System.out.println(name + " : " + i);
                Thread.sleep(200);
                synchronized (this) {
                    while (suspendedFlag) {
                        wait();
                    }
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(name+" exiting");
    }

    synchronized void suspend(){
        suspendedFlag = true;
    }

    synchronized void resume(){
        suspendedFlag = false;
        notify();
    }
}

public class ControlThread {
    public static void main(String[] args) {
        BrandNewThread ob1 = new BrandNewThread("One");
        BrandNewThread ob2 = new BrandNewThread("Three");

        try{
            Thread.sleep(1000);
            ob1.suspend();
            System.out.println("Suspending thread one");
            Thread.sleep(1000);
            ob1.resume();
            System.out.println("Resuming thread one");
            ob2.suspend();
            System.out.println("Suspending thread two");
            Thread.sleep(1000);
            ob2.resume();
            System.out.println("Resuming thread two");
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        try{
            System.out.println("Waiting for all thread....");
            ob1.t.join();
            ob2.t.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Main thread exiting....");
    }
}
