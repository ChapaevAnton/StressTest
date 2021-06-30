package com.example.stresstest.db;


import com.github.mikephil.charting.data.Entry;

import java.util.Arrays;
import java.util.List;

public class TestDatabase {


    public static List<Entry> getEntry() {

        Entry[] entries = {
                new Entry(1, 1),
                new Entry(2, 2),
                new Entry(3, 3),
                new Entry(4, 4),
                new Entry(5, 5)
        };

        return Arrays.asList(entries);
    }

}
