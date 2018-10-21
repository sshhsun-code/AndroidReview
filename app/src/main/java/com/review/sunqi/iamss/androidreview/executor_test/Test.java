package com.review.sunqi.iamss.androidreview.executor_test;

import android.os.SystemClock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            SystemClock.sleep(2000);
        }
    };

    private void main() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
        fixedThreadPool.execute(runnable);

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(runnable);

        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        singleThreadPool.execute(runnable);

        ScheduledExecutorService scheduleThreadPool = Executors.newScheduledThreadPool(5);
        scheduleThreadPool.schedule(runnable, 2000, TimeUnit.MILLISECONDS);

        scheduleThreadPool.scheduleAtFixedRate(runnable, 10, 1000, TimeUnit.MILLISECONDS);
    }




}
