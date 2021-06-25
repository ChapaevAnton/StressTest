package com.example.stresstest.benchmark;import com.example.stresstest.benchmark.cpu.CPUBenchmark;import com.example.stresstest.benchmark.file.FilesBenchmark;import com.example.stresstest.benchmark.hash.HashingBenchmark;import java.util.concurrent.ExecutorService;import java.util.concurrent.Executors;public class Benchmark {    private ExecutorService fileService = Executors.newSingleThreadExecutor();    private ExecutorService hashService = Executors.newSingleThreadExecutor();    private ExecutorService cpuService = Executors.newSingleThreadExecutor();    private boolean isRunning = false;    public void start(){        isRunning = true;        cpuService.execute(() -> {            while (isRunning) {                IBenchmark benchmark = new CPUBenchmark();                benchmark.initialize();                benchmark.run();            }        });        hashService.execute(() -> {            while (isRunning) {                IBenchmark benchmark = new HashingBenchmark();                benchmark.initialize();                benchmark.run();            }        });        fileService.execute(() -> {            while (isRunning) {                IBenchmark benchmark = new FilesBenchmark();                benchmark.initialize();                benchmark.run();            }        });    }    public void stop(){        isRunning = false;        fileService.shutdown();        hashService.shutdown();        cpuService.shutdown();        fileService = Executors.newSingleThreadExecutor();        hashService = Executors.newSingleThreadExecutor();        cpuService = Executors.newSingleThreadExecutor();    }}