package com.death.threadpool;

import java.util.List;

public interface Callback {
    void onResult(List<Integer> calculations);
}
