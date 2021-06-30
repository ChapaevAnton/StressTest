package com.example.stresstest.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.stresstest.data.LineDataSetCpuTest;
import com.example.stresstest.db.TestDatabase;
import com.example.stresstest.utils.Event;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;

import java.util.List;

public class StressTestViewModel extends AndroidViewModel {

    //данные Теста - временные
    private final List<Entry> entries = TestDatabase.getEntry();

    private final MutableLiveData<Event<Void>> startTest = new MutableLiveData<>();
    private final MutableLiveData<Event<Void>> stopTest = new MutableLiveData<>();
    private final MutableLiveData<LineData> lineDataCpuTest = new MutableLiveData<>();

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

    public LiveData<LineData> getLineDataCpuTest() {
        return lineDataCpuTest;
    }

    public void setLineDataCpuTest() {
        // QUESTION: 30.06.2021 Где это лучще начинать загружать данные на график?
        LineDataSetCpuTest lineDataSet = new LineDataSetCpuTest(entries, "test#1");
        LineData lineData = new LineData(lineDataSet);

        lineDataCpuTest.setValue(lineData);
    }


    //нажатие кнопок
    public void onStartTestClicked() {
        Log.d("TEST", "onStartTestClicked: click ");
        // QUESTION: 30.06.2021 Где это лучще начинать загружать данные на график?
        setLineDataCpuTest();

        startTest.setValue(new Event<>());
    }

    public void onStopTestClicked() {
        Log.d("TEST", "onStopTestClicked: click");
        stopTest.setValue(new Event<>());
    }




}
