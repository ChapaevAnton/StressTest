package com.example.stresstest.db;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface BatteryLogDao {

    @Insert
    void insertBatteryLog(BatteryLog batteryLog);

}
