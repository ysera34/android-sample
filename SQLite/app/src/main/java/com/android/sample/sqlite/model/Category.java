package com.android.sample.sqlite.model;

/**
 * Created by yoon on 2017. 7. 4..
 */

public class Category {

    private int mId;
    private int mNumber;
    private String mName;

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int number) {
        mNumber = number;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}
