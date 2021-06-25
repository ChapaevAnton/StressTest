package com.example.stresstest.benchmark.temp;


import java.util.concurrent.Callable;

public class CpuBenchmark implements Callable<String> {

    int threads;

    public CpuBenchmark(int threads) {
        this.threads = threads;
    }

    public static void runRegexCompileCpuTest(int thread) {
        for (int i = 0; i < thread; i++) {
            //new Thread(new RegexCompileCpuTest()).start();
        }
    }

    @Override
    public String call() {
        runRegexCompileCpuTest(threads);
        return "Task's execution";
    }
}
