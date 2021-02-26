package com.tahlia.annotation.retention_runtime.intent;

import java.io.Serializable;

import androidx.annotation.NonNull;

public class StudentSerializable implements Serializable {
    String name;

    public StudentSerializable(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return "StudentSerializable{" +
                "name='" + name + '\'' +
                '}';
    }
}
