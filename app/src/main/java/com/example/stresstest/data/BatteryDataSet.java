package com.example.stresstest.data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.data.LineDataSet;

import java.time.LocalDateTime;
import java.util.UUID;

final public class BatteryDataSet {

    private final String id;
    private final LocalDateTime startTime;
    private LocalDateTime endTime;
    private LineDataSet lineDataSet;

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

    public LineDataSet getLineDataSet() {
        return lineDataSet;
    }

    //setters
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setLineDataSet(LineDataSet lineDataSet) {
        this.lineDataSet = lineDataSet;
    }

    @Override
    public String toString() {
        return "BatteryDataSet{" +
                "id=" + id + "\n" +
                "startTime=" + startTime + "\n" +
                "endTime=" + endTime + "\n" +
                "lineDataSet=" + lineDataSet + "\n }";
    }
}
