package com.android.sample.sparsearray;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by yoon on 2017. 7. 16..
 */

public class ShopAdapter extends RecyclerView.Adapter<ShopHolder> {

    private ArrayList<ShopModel> mShopModels;
    private Context mContext;

    public ShopAdapter(ArrayList<ShopModel> shopModels, Context context) {
        mShopModels = shopModels;
        mContext = context;
    }

    @Override
    public ShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.list_item_shop, parent, false);
        return new ShopHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopHolder holder, int position) {
        holder.bindShop(mShopModels.get(position));
    }

    @Override
    public int getItemCount() {
        return mShopModels.size();
    }
}
