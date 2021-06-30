package com.example.stresstest.data;

final public class Entry {

    final private float axisX;
    final private float axisY;

    public Entry(float axisX, float axisY) {
        this.axisX = axisX;
        this.axisY = axisY;
    }

    public float getAxisX() {
        return axisX;
    }

    public float getAxisY() {
        return axisY;
    }

}
