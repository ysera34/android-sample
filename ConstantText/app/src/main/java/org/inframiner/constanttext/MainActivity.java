package org.inframiner.constanttext;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnFocusChangeListener {

    private static final String PRE_FIX_TEXT = "http:// ";
    private static final String PRE_FIX_TEXT2 = "file:// ";
    private PrefixTextWatcher mPrefixTextWatcher;
    private EditText mEditText;
    private EditText mEditText2;
    private Button mAdd1Button;
    private Button mRemove1Button;
    private Button mAdd2Button;
    private Button mRemove2Button;

    private InputMethodManager mInputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPrefixTextWatcher = new PrefixTextWatcher() {
            @Override
            void notifyTextChanged() {
                Toast.makeText(getApplicationContext(),
                        "alert changed", Toast.LENGTH_SHORT).show();
            }
        };

        mEditText = (EditText) findViewById(R.id.edit_text);
        mEditText.setOnFocusChangeListener(this);

//        mEditText.setText(PRE_FIX_TEXT);
//        Selection.setSelection(mEditText.getText(), mEditText.getText().length());

        mEditText2 = (EditText) findViewById(R.id.edit_text_2);
        mAdd1Button = (Button) findViewById(R.id.add_1_button);
        mAdd1Button.setOnClickListener(this);
        mRemove1Button = (Button) findViewById(R.id.remove_1_button);
        mRemove1Button.setOnClickListener(this);
        mAdd2Button = (Button) findViewById(R.id.add_2_button);
        mAdd2Button.setOnClickListener(this);
        mRemove2Button = (Button) findViewById(R.id.remove_2_button);
        mRemove2Button.setOnClickListener(this);

        mInputMethodManager = (InputMethodManager) getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (mInputMethodManager.isAcceptingText()) {
            Toast.makeText(getApplicationContext(),
                    "Software Keyboard was shown", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),
                    "Software Keyboard was not shown", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_1_button:
                addPrefixTextChangedListener(mEditText, PRE_FIX_TEXT);
                break;
            case R.id.remove_1_button:
                removePrefixTextChangedListener(mEditText);
                break;
            case R.id.add_2_button:
                addPrefixTextChangedListener(mEditText2, PRE_FIX_TEXT2);
                break;
            case R.id.remove_2_button:
                removePrefixTextChangedListener(mEditText2);
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.edit_text:
                if (hasFocus) {
                    addPrefixTextChangedListener((EditText) v, PRE_FIX_TEXT);
                }
                break;
        }
    }

    private boolean isHasTextChangedListener = false;

    private void addPrefixTextChangedListener(EditText editText, String prefixText) {
        mPrefixTextWatcher.setPrefixText(editText, prefixText);
        editText.addTextChangedListener(mPrefixTextWatcher);
        isHasTextChangedListener = true;
    }

    private void removePrefixTextChangedListener(EditText editText) {
        mInputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        editText.removeTextChangedListener(mPrefixTextWatcher);
        editText.setText("");
        isHasTextChangedListener = false;
    }

    @Override
    public void onBackPressed() {
        if (isHasTextChangedListener) {
            showAlertDialog();
        } else {
            super.onBackPressed();
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure you want to cancel?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removePrefixTextChangedListener(mEditText);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
}
