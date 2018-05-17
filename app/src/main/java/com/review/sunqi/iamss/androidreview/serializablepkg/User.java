package com.review.sunqi.iamss.androidreview.serializablepkg;

import java.io.Serializable;

/**
 * Created by sunqi on 2018/5/17.
 */

public class User implements Serializable {

    private static final long serialVersionUID = -2083503801443301445L;

    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
