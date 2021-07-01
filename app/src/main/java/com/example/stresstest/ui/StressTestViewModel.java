package com.example.stresstest.ui;

import android.app.Application;
import android.graphics.Color;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.stresstest.utils.Event;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.Collections;
import java.util.List;

public class StressTestViewModel extends AndroidViewModel {

    //данные Теста - временные
    private List<Entry> entries;

    private LineDataSet lineDataSet = createLineDataSet();
    private LineData lineData = new LineData(lineDataSet);


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
    public void setDataLineChart() {

        dataLineChart.postValue(lineData);
    }


    //нажатие кнопок
    private float x = 0f;
    private float y = 100f;
    public void onStartTestClicked() {
        Log.d("TEST", "onStartTestClicked: click ");
        // QUESTION: 30.06.2021 Где это лучще начинать загружать данные на график?
        lineData = dataLineChart.getValue();

        if (lineData != null) {
            ILineDataSet set = lineData.getDataSetByIndex(0);

            if (set == null) {
                set = createLineDataSet();
                lineData.addDataSet(set);
            }
            x+=10f;
            y-=0.1f;
            lineData.addEntry(new Entry(x, y), 0);
            lineData.notifyDataChanged();
            Log.d("TEST", "onStartTestClicked: " + lineData.getDataSets());
        }


        dataLineChart.setValue(lineData);

        startTest.setValue(new Event<>());
    }

    private LineDataSet createLineDataSet() {
        LineDataSet lineDataSet = new LineDataSet(null, "battery");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        lineDataSet.setLineWidth(3f);
        lineDataSet.setColor(Color.RED);
        lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSet.setCubicIntensity(0.2f);
        return lineDataSet;
    }

    public void onStopTestClicked() {
        Log.d("TEST", "onStopTestClicked: click");
        stopTest.setValue(new Event<>());
    }


}
