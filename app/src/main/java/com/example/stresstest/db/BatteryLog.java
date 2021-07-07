package com.example.stresstest.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.github.mikephil.charting.data.Entry;

import java.time.LocalDateTime;
import java.util.List;

@Entity(tableName = "battery_log")
public class BatteryLog {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "start_time")
    private LocalDateTime startTime;

    @ColumnInfo(name = "end_time")
    private LocalDateTime endTime;

    @ColumnInfo(name = "line_value_set")
    private List<Entry> lineValueSet;

    public BatteryLog(LocalDateTime startTime, LocalDateTime endTime, List<Entry> lineValueSet) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.lineValueSet = lineValueSet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }


    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<Entry> getLineValueSet() {
        return lineValueSet;
    }

    public void setLineValueSet(List<Entry> lineValueSet) {
        this.lineValueSet = lineValueSet;
    }
}
