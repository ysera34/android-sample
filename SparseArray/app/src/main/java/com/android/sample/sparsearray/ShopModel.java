package com.android.sample.sparsearray;

import android.util.SparseArray;

/**
 * Created by yoon on 2017. 7. 16..
 */

public class ShopModel {

    private int mId;
    private SparseArray<String> mAddresses;
    private String mAddress;
    private String mAddress1;
    private String mAddress2;
    private String mAddress3;

    public ShopModel() {
        mAddresses = new SparseArray<>(4);
        mAddress = "initial address";
        mAddress1 = "initial address1";
        mAddress2 = "initial address2";
        mAddress3 = "initial address3";
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public SparseArray<String> getAddresses() {
        return mAddresses;
    }

    public void setAddresses(SparseArray<String> addresses) {
        mAddresses = addresses;
    }

    public int getAddressSize() {
        return mAddresses.size();
    }

    public String getAddress() {
//        return mAddress;
        return mAddresses.get(0, mAddress);
    }

    public void setAddress(String address) {
//        mAddress = address;
        mAddresses.put(0, address);
    }

    public String getAddress1() {
//        return mAddress1;
        return mAddresses.get(1, mAddress1);
    }

    public void setAddress1(String address1) {
//        mAddress1 = address1;
        mAddresses.put(1, address1);
    }

    public String getAddress2() {
//        return mAddress2;
        return mAddresses.get(2, mAddress2);
    }

    public void setAddress2(String address2) {
//        mAddress2 = address2;
        mAddresses.put(2, address2);
    }

    public String getAddress3() {
//        return mAddress3;
        return mAddresses.get(3, mAddress3);
    }

    public void setAddress3(String address3) {
//        mAddress3 = address3;
        mAddresses.put(3, address3);
    }
}
