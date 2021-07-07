package com.example.stresstest.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BatteryLogDao {

    @Query("SELECT * FROM battery_log")
    List<BatteryLog> getBatteryLogList();

    @Insert
    void insertBatteryLog(BatteryLog batteryLog);

}
