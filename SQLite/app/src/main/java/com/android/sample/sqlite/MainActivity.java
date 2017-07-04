package com.android.sample.sqlite;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.sample.sqlite.model.Category;
import com.android.sample.sqlite.model.CategoryStorage;
import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (BuildConfig.DEBUG) {
            Context context = getApplicationContext();
            Stetho.initializeWithDefaults(this);
        }

        CategoryStorage categoryStorage = CategoryStorage.get(getApplicationContext());
        for (int i = 0; i < 5; i++) {
            Category category = new Category();
            category.setId(i);
            category.setNumber(i);
            category.setName("category name : " + i);
            categoryStorage.addCategory(category);
        }
    }
}
