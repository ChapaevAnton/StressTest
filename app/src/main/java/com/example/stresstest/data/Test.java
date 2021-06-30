package com.example.stresstest.data;

import java.time.LocalDateTime;
import java.util.List;

final public class Test {

    final private String label;
    final private LocalDateTime startTime;
    final private LocalDateTime endTime;
    final private List<Entry> coordinateList;

    public Test(String label, LocalDateTime startTime, LocalDateTime endTime, List<Entry> coordinateList) {
        this.label = label;
        this.startTime = startTime;
        this.endTime = endTime;
        this.coordinateList = coordinateList;
    }

    public String getLabel() {
        return label;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public List<Entry> getCoordinateList() {
        return coordinateList;
    }

}
