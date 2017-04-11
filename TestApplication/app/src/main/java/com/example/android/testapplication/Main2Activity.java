package com.example.android.testapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by yoon on 2017. 4. 11..
 */

public class Main2Activity extends AppCompatActivity {

    private static final String TAG = Main2Activity.class.getSimpleName();

    private static final String BYTE_NUMBER = "com.example.android.byte_number";
    private static final String SHORT_NUMBER = "com.example.android.short_number";
    private static final String INT_NUMBER = "com.example.android.int_number";

    public static Intent newIntent(Context packageContext, byte byteNumber) {
        Intent intent = new Intent(packageContext, Main2Activity.class);
        intent.putExtra(BYTE_NUMBER, byteNumber);
        return intent;
    }

    public static Intent newIntent(Context packageContext, short shortNumber) {
        Intent intent = new Intent(packageContext, Main2Activity.class);
        intent.putExtra(SHORT_NUMBER, shortNumber);
        return intent;
    }

    public static Intent newIntent(Context packageContext, int intNumber) {
        Intent intent = new Intent(packageContext, Main2Activity.class);
        intent.putExtra(INT_NUMBER, intNumber);
        return intent;
    }

    private byte mByteNumber;
    private short mShortNumber;
    private int mIntNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "initial value");
        Log.d(TAG, String.valueOf(mByteNumber));
        Log.d(TAG, String.valueOf(mShortNumber));
        Log.d(TAG, String.valueOf(mIntNumber));

        mByteNumber = getIntent().getByteExtra(BYTE_NUMBER, (byte) -1);
        mShortNumber = getIntent().getShortExtra(SHORT_NUMBER, (short) -1);
        mIntNumber = getIntent().getIntExtra(INT_NUMBER, -1);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.d(TAG, "SDK_INT >= N");
            Log.d(TAG, String.valueOf(mByteNumber));
            Log.d(TAG, String.valueOf(mShortNumber));
            Log.d(TAG, String.valueOf(mIntNumber));
        } else {
            Log.d(TAG, "SDK_INT < N");
            Log.d(TAG, String.valueOf(mByteNumber));
            Log.d(TAG, String.valueOf(mShortNumber));
            Log.d(TAG, String.valueOf(mIntNumber));
        }
    }
}
