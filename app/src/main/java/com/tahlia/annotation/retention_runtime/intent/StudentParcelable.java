package com.tahlia.annotation.retention_runtime.intent;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class StudentParcelable implements Parcelable {
    String name;

    public StudentParcelable(String name) {
        this.name = name;
    }

    protected StudentParcelable(Parcel in) {
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StudentParcelable> CREATOR = new Creator<StudentParcelable>() {
        @Override
        public StudentParcelable createFromParcel(Parcel in) {
            return new StudentParcelable(in);
        }

        @Override
        public StudentParcelable[] newArray(int size) {
            return new StudentParcelable[size];
        }
    };

    @NonNull
    @Override
    public String toString() {
        return "StudentParcelable{" +
                "name='" + name + '\'' +
                '}';
    }
}
