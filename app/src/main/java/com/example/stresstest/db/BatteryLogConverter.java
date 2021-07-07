package com.example.stresstest.db;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class BatteryLogConverter {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public long fromLocalDateTimeToLong(LocalDateTime dateTime) {
        ZonedDateTime zdt = ZonedDateTime.of(dateTime, ZoneId.systemDefault());
        return zdt.toInstant().toEpochMilli();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @TypeConverter
    public LocalDateTime fromLongToLocalDateTime(long dateTime){
        return new Date(dateTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
