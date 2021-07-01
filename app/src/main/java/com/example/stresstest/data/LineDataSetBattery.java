package com.example.stresstest.data;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final public class LineDataSetBattery extends LineDataSet {


    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public LineDataSetBattery(){
        super(Collections.singletonList(new Entry(0f, 10f)),"battery charge");
    }

    public LineDataSetBattery(List<Entry> coordinateList, String label) {
        super(coordinateList,label);
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }



}
