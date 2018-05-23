package com.review.sunqi.iamss.androidreview.aidl2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sunqi on 2018/5/23.
 */

public class UserInfo implements Parcelable {

    private String name;
    private String address;
    private int age;

    public UserInfo(String name, String address, int age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    protected UserInfo(Parcel in) {
        name = in.readString();
        address = in.readString();
        age = in.readInt();
    }

    public static final Creator<UserInfo> CREATOR = new Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(address);
        parcel.writeInt(age);
    }
}
