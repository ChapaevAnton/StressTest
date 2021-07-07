package com.example.stresstest.db;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class BatteryLogConverter {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public long fromLocalDateTimeToLong(LocalDateTime localDateTime) {
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

}
