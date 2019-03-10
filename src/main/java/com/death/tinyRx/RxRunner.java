package com.death.tinyRx;

import java.util.Arrays;

public class RxRunner {
    public static void main(String[] args) {
        TinyObservable.emit(Arrays.asList(1, 2, 3, 4))
                .filter(new Predicate() {
                    @Override
                    public boolean test(Integer item) {
                        return item % 2 == 0;
                    }
                }).map(new Mapper() {
            @Override
            public Integer apply(Integer item) {
                return item * 10;
            }
        })
                .subscribe(new Subscriber() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println(item);
                    }
                });
    }
}
