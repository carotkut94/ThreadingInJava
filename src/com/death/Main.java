package com.death;


// A simple thread program for getting a hook onto the Main Thread and its manipulation
public class Main {

    public static void main(String[] args) {
        // for getting the reference to main thread
        Thread t = Thread.currentThread();

        System.out.println(String.format("Main Thread %s", t.getName()));
        t.setName("Trail Blazer");
        System.out.println(String.format("Main Thread After Name Change %s", t.getName()));

        try{
            for(int i=5; i>0;i--){
                System.out.println(i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
