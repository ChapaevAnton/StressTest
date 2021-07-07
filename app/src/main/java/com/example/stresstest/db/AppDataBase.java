package com.example.stresstest.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = BatteryLog.class, version = 1)
@TypeConverters({BatteryLogConverter.class})
public abstract class AppDataBase extends RoomDatabase {
    public abstract BatteryLogDao batteryLogDao();
}
