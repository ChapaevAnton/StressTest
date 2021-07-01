package com.example.stresstest.data;

import com.github.mikephil.charting.data.LineDataSet;

import java.time.LocalDateTime;

final public class LineDataSetBattery extends LineDataSet {


    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public LineDataSetBattery(){
        super(null,"battery charge");
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }



}
