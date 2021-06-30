package com.example.stresstest.data;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;

import java.time.LocalDateTime;
import java.util.List;

final public class LineDataSetCpuTest extends LineDataSet {


    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public LineDataSetCpuTest(List<Entry> coordinateList, String label) {
        super(coordinateList,label);
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }



}
