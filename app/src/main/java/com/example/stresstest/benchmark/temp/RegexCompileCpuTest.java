package com.example.stresstest.benchmark.temp;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class RegexCompileCpuTest implements Callable<String> {

    @Override
    public String call() {

            Log.d("TEST", "RegexCompileCpuTest RUN: OK");
            while (true) {
                Pattern p = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+(?:[A-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum)\b");
            }

    }

    private void startTest(){
        Executors.newSingleThreadExecutor().execute(() -> {
            // TODO: 24.06.2021 Отдельный поток
            Callable<String> callable = new RegexCompileCpuTest();
            List<Callable<String>> callableTasks = new ArrayList<>();
            callableTasks.add(callable);
            callableTasks.add(callable);




            ExecutorService executorService = Executors.newFixedThreadPool(5);
            try {
                executorService.invokeAny(callableTasks);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        });
    }

}
