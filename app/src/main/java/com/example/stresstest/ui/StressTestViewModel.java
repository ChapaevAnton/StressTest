package com.example.stresstest.ui;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.stresstest.data.BatteryDataSet;
import com.example.stresstest.utils.Event;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class StressTestViewModel extends AndroidViewModel {

    //набор данных
    private final LineDataSet lineDataSet = new LineDataSet(null, "battery charge");
    private final LineData lineData = new LineData(lineDataSet);
    private BatteryDataSet batteryDataSet;

    //старт тест
    private final MutableLiveData<Event<Void>> startTest = new MutableLiveData<>();
    //стоп тест
    private final MutableLiveData<Event<Void>> stopTest = new MutableLiveData<>();
    //график
    private final MutableLiveData<LineData> dataLineChart = new MutableLiveData<>();
    //уровень заряда батареи
    private final MutableLiveData<String> chargeLevel = new MutableLiveData<>();
    //температура батареи
    private final MutableLiveData<String> temperatureLevel = new MutableLiveData<>();

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

    public LiveData<String> getChargeLevel() {
        return chargeLevel;
    }

    public LiveData<String> getTemperatureLevel() {
        return temperatureLevel;
    }

    //инизилизация
    public void initDataLineChart() {
        if (lineData.getEntryCount() == 0) {
            lineData.addEntry(new Entry(0f, 0f), 0);
            dataLineChart.postValue(lineData);
        }
    }


    private final AtomicBoolean interrupt = new AtomicBoolean();

    //стартуем поток на клик кнопки
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onStartTestClicked() {
        Log.d("TEST", "onStartTestClicked: click ");
        lineDataSet.clear();
        interrupt.set(true);
        //иницилизируем набор данных - уникальный id и время старта
        batteryDataSet = new BatteryDataSet();
        Executors.newSingleThreadExecutor().execute(() -> {
            Log.d("TEST", "START");
            long minuteOfTime = 0;
            while (interrupt.get()) {
                minuteOfTime++;
                getCurrentTemperatureLevel();
                addEntry(minuteOfTime, getCurrentChargeLevel());
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
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onStopTestClicked() {
        Log.d("TEST", "onStopTestClicked: click");
        interrupt.set(false);
        Log.d("TEST", "STOP");
        //передаем набор данных и время окончания
        batteryDataSet.setLineValueSet(lineDataSet.getValues());
        batteryDataSet.setEndTime(LocalDateTime.now());
        Log.d("TEST", "batteryDataSet: " + batteryDataSet);
        stopTest.setValue(new Event<>());
    }

    //добавляем новую точку на графике
    private void addEntry(float timestamp, float chargeLevel) {
        lineData.addEntry(new Entry(timestamp, chargeLevel), 0);
        Log.d("TEST", "lineData.addEntry: " + lineData.getDataSets());
        dataLineChart.postValue(lineData);
    }

    //уровень заряда батареи
    private final IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    private Intent batteryStatus;

    private float getCurrentChargeLevel() {
        batteryStatus = getApplication().getApplicationContext().registerReceiver(null, intentFilter);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        Log.d("TEST", "getCurrentChargeLevel: " + level);
        Log.d("TEST", "getCurrentChargeScale: " + scale);
        float batteryPct = level * 100 / (float) scale;
        chargeLevel.postValue(String.valueOf(batteryPct));
        return batteryPct;
    }

    //температура батареи
    private float getCurrentTemperatureLevel() {
        batteryStatus = getApplication().getApplicationContext().registerReceiver(null, intentFilter);
        int temperature = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0);
        float batteryTempCel = (float) temperature / 10;
        Log.d("TEST", "getCurrentTemperatureLevel: " + batteryTempCel);
        temperatureLevel.postValue(String.valueOf(batteryTempCel));
        return batteryTempCel;
    }

}