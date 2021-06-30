package com.example.stresstest.ui;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.stresstest.utils.Event;

public class StressTestViewModel extends AndroidViewModel {

    private final MutableLiveData<Event<Void>> startTest = new MutableLiveData<>();
    private final MutableLiveData<Event<Void>> stopTest = new MutableLiveData<>();

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

    //нажатие кнопок
    public void onStartTestClicked() {
        Log.d("TEST", "onStartTestClicked: click ");
        startTest.setValue(new Event<>());
    }

    public void onStopTestClicked() {
        Log.d("TEST", "onStopTestClicked: click");
        startTest.setValue(new Event<>());
    }

}
