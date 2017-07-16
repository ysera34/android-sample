package com.android.sample.sparsearray;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ShopAdapter mShopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mShopAdapter = new ShopAdapter(getNewShops(), getApplicationContext());
        mRecyclerView.setAdapter(mShopAdapter);
    }

    private ArrayList<ShopModel> getNewShops() {
        ArrayList<ShopModel> shopModels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ShopModel shopModel = new ShopModel();
            shopModel.setId(i);
            shopModel.setAddress("new shopInfo address: " + i);
            shopModel.setAddress1("new shopInfo address1: " + i);
            shopModel.setAddress2("new shopInfo address2: " + i);
            if (i % 2 == 0) {
                shopModel.setAddress3("new shopInfo address3: " + i);
            }
            shopModels.add(shopModel);
        }
        return shopModels;
    }
}
