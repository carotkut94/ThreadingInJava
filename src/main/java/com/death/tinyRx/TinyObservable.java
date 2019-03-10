package com.death.tinyRx;

import java.util.ArrayList;
import java.util.List;

public class TinyObservable {
    public static TinyObservable emit(List<Integer> list) {
        return new TinyObservable(list);
    }

    private List<Integer> list;

    TinyObservable(List<Integer> list) {
        this.list = list;
    }

    public TinyObservable filter(Predicate predicate){
        List<Integer> filteredList = new ArrayList<>();
        for (Integer value:list){
            if(predicate.test(value))
                filteredList.add(value);
        }
        list = filteredList;
        return this;
    }

    public TinyObservable map(Mapper mapper){
        List<Integer> mappedList = new ArrayList<>();
        for (Integer value:list){
            mappedList.add(mapper.apply(value));
        }
        list = mappedList;
        return this;
    }

    public void subscribe(Subscriber subscriber){
        for (Integer value:list){
            subscriber.onNext(value);
        }
    }

}
