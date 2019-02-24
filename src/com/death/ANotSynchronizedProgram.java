package com.death;


class CallMe{

    /**
     * This variant is not synchronized and thus will produce unexpected output
     * @param message

    void call(String message){
        System.out.print("["+message);
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.print("]");

    on my machine it gave this output [Hello[World[Synch]]]
    }*/

    /**
     *This is the same variant of above method code, and thus uses monitor mechanism to
     * achieve the synchronization
     *
     * and this gave the expected output as [Hello][World][Synch]
     */
    synchronized void call(String message) {
        System.out.print("[" + message);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print("]");
    }
}

class CallerProgram implements Runnable{

    String message;
    CallMe target;
    Thread t;

    public CallerProgram(CallMe target, String s){
        this.target = target;
        message = s;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        target.call(message);
    }
}

public class ANotSynchronizedProgram {
    public static void main(String... args){
        CallMe target = new CallMe();
        CallerProgram ob1 = new CallerProgram(target,"Hello");
        CallerProgram ob2 = new CallerProgram(target,"Synch");
        CallerProgram ob3 = new CallerProgram(target,"World");

        try{
            ob1.t.join();
            ob2.t.join();
            ob3.t.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
