package com.example.stresstest;

import android.os.Bundle;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import com.example.stresstest.benchmark.RegexCompileCpuTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onStart() {
        super.onStart();

        Executors.newSingleThreadExecutor().execute(() -> {
            // TODO: 24.06.2021 Отдельный поток
            Callable<String> callable = new RegexCompileCpuTest();
            List<Callable<String>> callableTasks = new ArrayList<>();
            callableTasks.add(callable);
            callableTasks.add(callable);
            callableTasks.add(callable);


            ExecutorService executorService = Executors.newFixedThreadPool(5);
            try {
                executorService.invokeAny(callableTasks);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }

        });

        Executors.newSingleThreadExecutor().execute(() -> {




                while (true) {
                    Log.d("TEST", "LOAD: " );
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

        });


    }
}