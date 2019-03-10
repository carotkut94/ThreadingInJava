package com.death.rxlearn;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import java.util.Arrays;
import java.util.List;

public class RxRunner {

    static int result = 0;
    static String[] titleList = {"Lord", "Mount", "Baton"};
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

        /*
        Now lets do some transformation to data
         */

        //TODO: Here we are going to use in the Map operator and will change the case of letters in array

        Observable.fromArray(letters)
                .map(String::toUpperCase)
                .subscribe(System.out::println);

        //TODO: Lets use FlatMap to flatten the nested observable
        Observable.just("book1", "book2")
                .flatMap(s->getTitle())
                .subscribe(System.out::println);

        // Here for two items book1 and book2 flatMap will be called for both of them
        // and it will call in the getTitle which return observable
        // and then this will call in the onNext of subscribe with the data
        // and will print Lord\nMount\Baton 2 times, depending upon the number of items in
        // just(...)

        //TODO: Lets use the groupBy operator to group the numbers in even or odd
        List<Integer> n = Arrays.asList(1,2,3,4,5,6,7,8);
        Observable.fromIterable(n)
                .groupBy(num->0==(num%2)?"EVEN":"ODD")
                .subscribe(
                        group->group.subscribe(
                                (data)->{
                                    if(group.getKey().toString().equals("EVEN")){
                                        System.out.println("EVEN "+data);
                                    }else{
                                        System.out.println("ODD "+ data);
                                    }
                                }
                        )
                );

        //TODO: Lets filter out the even numbers and print them
        Observable.fromIterable(n)
                .filter(i->(i%2==0))
                .subscribe(System.out::println);
    }

    static Observable<String> getTitle(){
        return Observable.fromArray(titleList);
    }
}
