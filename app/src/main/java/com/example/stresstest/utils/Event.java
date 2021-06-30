package com.example.stresstest.utils;

final public class Event<T> {

    private T content;
    private boolean isHandled = false;

    public Event() {
    }

    public Event(T content) {
        this.content = content;
    }

    public T getContent() {
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
