package com.example.stresstest.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.stresstest.data.LineDataSetBattery;
import com.example.stresstest.utils.Event;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class StressTestViewModel extends AndroidViewModel {

    //данные Теста - временные
    private List<Entry> entries;

    private final LineDataSet lineDataSet = new LineDataSetBattery();
    private final LineData lineData = new LineData(lineDataSet);


    private final MutableLiveData<Event<Void>> startTest = new MutableLiveData<>();
    private final MutableLiveData<Event<Void>> stopTest = new MutableLiveData<>();
    private final MutableLiveData<LineData> dataLineChart = new MutableLiveData<>();

    //конструктор
    public StressTestViewModel(@NonNull Application application) {
        super(application);
    }

    //события
    public LiveData<Event<Void>> getStartTest() {
        return startTest;
    }

    public LiveData<Event<Void>> getStopTest() {
        return stopTest;
    }

    //данные
    public LiveData<LineData> getDataLineChart() {
        return dataLineChart;
    }

    //инизилизация
    public void initDataLineChart() {
        lineData.addEntry(new Entry(0f, 0f), 0);
        dataLineChart.postValue(lineData);
    }


    private final AtomicBoolean interrupt = new AtomicBoolean();

    //стартуем поток на клик кнопки
    public void onStartTestClicked() {
        Log.d("TEST", "onStartTestClicked: click ");
        lineDataSet.clear();
        interrupt.set(true);
        Executors.newSingleThreadExecutor().execute(() -> {
            Log.d("TEST", "START");
            int x = 0;
            while (interrupt.get()) {
                x++;
                addEntry(x, new Random().nextInt(100));
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        startTest.setValue(new Event<>());
    }

    //останавливаем поток на клик кнопки
    public void onStopTestClicked() {
        Log.d("TEST", "onStopTestClicked: click");
        interrupt.set(false);
        Log.d("TEST", "STOP");
        Log.d("TEST", "lineData.addEntry: " + lineData.getDataSets());
        stopTest.setValue(new Event<>());
    }

    //добавляем новую точку на графике
    private void addEntry(float timestamp, float chargeLevel) {
        lineData.addEntry(new Entry(timestamp, chargeLevel), 0);
        Log.d("TEST", "lineData.addEntry: " + lineData.getDataSets());
        dataLineChart.postValue(lineData);
    }


}