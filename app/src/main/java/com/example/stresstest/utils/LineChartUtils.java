package com.example.stresstest.utils;

import androidx.databinding.BindingAdapter;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.LineData;

public class LineChartUtils {

    @BindingAdapter({"app:setLineData"})
    public static void setLineData(LineChart lineChart, LineData lineData) {
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

}
