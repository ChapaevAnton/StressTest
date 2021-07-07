package com.example.stresstest.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.data.Entry;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

final public class BatteryDataSet {

    private final String id;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<Entry> lineValueSet;

    //init
    @RequiresApi(api = Build.VERSION_CODES.O)
    public BatteryDataSet() {
        this.id = UUID.randomUUID().toString();
        this.startTime = LocalDateTime.now();
    }

    //getters
    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<Entry> getLineValueSet() {
        return lineValueSet;
    }

    //setters
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setLineValueSet(List<Entry> lineValueSet) {
        this.lineValueSet = lineValueSet;
    }

    @Override
    public String toString() {
        return "BatteryDataSet{\n" +
                "id=" + id + "\n" +
                "startTime=" + startTime + "\n" +
                "endTime=" + endTime + "\n" +
                "lineValueSet=" + lineValueSet + "}";
    }
}
