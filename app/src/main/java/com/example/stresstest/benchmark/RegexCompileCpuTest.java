package com.example.stresstest.benchmark;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.regex.Pattern;

public class RegexCompileCpuTest implements Callable<String> {

    @Override
    public String call() {

            Log.d("TEST", "RegexCompileCpuTest RUN: OK");
            while (true) {
                Pattern p = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+(?:[A-Z]{2}|com|org|net|edu|gov|mil|biz|info|mobi|name|aero|asia|jobs|museum)\b");
            }

    }
}
