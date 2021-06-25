package com.example.stresstest.utils;

import android.os.Bundle;

public class Event {

    final Bundle content;
    private boolean isHandled = false;

    public Event(Bundle content) {
        this.content = content;
    }

    public Bundle getContent() {
        return content;
    }

    public boolean isHandled() {
        if (isHandled) {
            return false;
        } else {
            isHandled = true;
            return true;
        }
    }
}
