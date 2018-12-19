package com.isever.sergn.homeproject.controllers;

import android.os.Parcelable;

final class Parcel implements Parcelable {
    private final String cityName;
    private final int index;

    Parcel(int index, String cityName) {
        this.cityName = cityName;
        this.index = index;
    }

    String getCityName() {
        return cityName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.cityName);
        dest.writeInt(this.index);
    }

    private Parcel(android.os.Parcel in) {
        this.cityName = in.readString();
        this.index = in.readInt();
    }

    public static final Creator<Parcel> CREATOR = new Creator<Parcel>() {
        @Override
        public Parcel createFromParcel(android.os.Parcel source) {
            return new Parcel(source);
        }

        @Override
        public Parcel[] newArray(int size) {
            return new Parcel[size];
        }
    };
}
