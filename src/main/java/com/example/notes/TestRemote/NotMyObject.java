package com.example.notes.TestRemote;

import java.io.Serializable;

public class NotMyObject implements Serializable {
    private String name;
    private int value;

    public NotMyObject(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
