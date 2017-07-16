package com.android.sample.sparsearray;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yoon on 2017. 7. 16..
 */

public class ShopHolder extends RecyclerView.ViewHolder {

    private ShopModel mShopModel;
    private ArrayList<TextView> mShopAddressTextViews;

//    private TextView mShopAddressTextView;
//    private TextView mShopAddressTextView1;
//    private TextView mShopAddressTextView2;
//    private TextView mShopAddressTextView3;

    public ShopHolder(View itemView) {
        super(itemView);
        mShopAddressTextViews = new ArrayList<>();
        int[] shopAddressTextViewResIdArray = {
                R.id.list_item_shop_address, R.id.list_item_shop_address2,
                R.id.list_item_shop_address3, R.id.list_item_shop_address4,};
//        for (int i = 0; i < shopAddressTextViewResIdArray.length; i++) {
//            mShopAddressTextViews.add((TextView) itemView.findViewById(shopAddressTextViewResIdArray[i]));
//        }
        for (int i : shopAddressTextViewResIdArray) {
            mShopAddressTextViews.add((TextView) itemView.findViewById(i));
        }
    }

    public void bindShop(ShopModel shopModel) {
        mShopModel = shopModel;
//        for (int i = 0; i < mShopModel.getAddressSize(); i++) {
//            mShopAddressTextViews.get(i).setText(mShopModel.getAddresses().get(i));
//        }
        mShopAddressTextViews.get(0).setText(mShopModel.getAddress());
        mShopAddressTextViews.get(1).setText(mShopModel.getAddress1());
        mShopAddressTextViews.get(2).setText(mShopModel.getAddress2());
        mShopAddressTextViews.get(3).setText(mShopModel.getAddress3());
    }
}
