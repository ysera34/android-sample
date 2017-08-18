package com.android.sample.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private static final String CLIENT_ID = "";
    private static final String CLIENT_PASSWORD = "";

    private String mBookName;
    private String mBookPrice;
    private String mBookPublishDate;
    private String mAuthorName;
    private String mPublisherName;

    private TextView mISBNTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mISBNTextView = (TextView) findViewById(R.id.isbn_text_view);
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        mISBNTextView.setText(getString(R.string.isbn_format, result.getContents()));
        Toast.makeText(this, "ISBN:" + result.getContents(), Toast.LENGTH_SHORT).show();
    }
}
