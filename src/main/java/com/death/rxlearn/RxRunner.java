package com.death.rxlearn;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.List;

public class RxRunner {

    static int result = 0;
    public static void main(String[] args) {

        /**
         * A Simple observable and subscriber
         */
        Observable<String> observable = Observable.just("Hello World");
        observable.subscribe(System.out::println);

        /**
         * Lets integer numbers and calculate sum of all elements
         */
        Observable<Integer> numbers = Observable.just(1,2,3,4,5,6,7,8,9,10);
        numbers.subscribe(
                i->result+=i,
                Throwable::printStackTrace,
                ()->System.out.println(result)
        );

        String[] letters = {"a", "b", "c", "d", "e"};
        Observable<String> stringObservable = Observable.fromArray(letters);
        stringObservable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable disposable) {
                System.out.println("Subscribed");
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        });

        /*The above line of code be rewritten as
         *below
         */
        stringObservable.subscribe(
                System.out::println,
                Throwable::printStackTrace,
                ()->System.out.println("Completed"),
                disposable -> System.out.println("Subscribed")
        );
        // Here we can see a huge line of code reduction and it makes it more
        // Readable
    }
}
