package org.inframiner.constanttext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PRE_FIX_TEXT = "http://";
    private static final String PRE_FIX_TEXT2 = "file://";
    private PrefixTextWatcher mPrefixTextWatcher;
    private EditText mEditText;
    private EditText mEditText2;
    private Button mAdd1Button;
    private Button mRemove1Button;
    private Button mAdd2Button;
    private Button mRemove2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit_text);
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


        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // case1
//                if (!s.toString().contains(PRE_FIX_TEXT)) {
//                    mEditText.setText(PRE_FIX_TEXT);
//                    Selection.setSelection(mEditText.getText(), mEditText.getText().length());
//                }


                // case2
//                String prefix = "http://";
//                if (!s.toString().startsWith(prefix)) {
//                    String cleanString;
//                    String deletedPrefix = prefix.substring(0, prefix.length() - 1);
//                    if (s.toString().startsWith(deletedPrefix)) {
//                        cleanString = s.toString().replaceAll(deletedPrefix, "");
//                    } else {
//                        cleanString = s.toString().replaceAll(prefix, "");
//                    }
//                    mEditText.setText(prefix + cleanString);
//                    mEditText.setSelection(prefix.length());
//
//                }
            }
        });


        // case 3
//        mEditText.addTextChangedListener(new PrefixTextWatcher(mEditText, PRE_FIX_TEXT) {});

        // case 4
        mPrefixTextWatcher = new PrefixTextWatcher();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_1_button:
//                mPrefixTextWatcher.setPrefixText(mEditText, PRE_FIX_TEXT);
//                mEditText.addTextChangedListener(mPrefixTextWatcher);
                addPrefixTextChangedListener(mEditText, PRE_FIX_TEXT);
                break;
            case R.id.remove_1_button:
//                mEditText.removeTextChangedListener(mPrefixTextWatcher);
                removePrefixTextChangedListener(mEditText);
                break;
            case R.id.add_2_button:
//                mPrefixTextWatcher.setPrefixText(mEditText2, PRE_FIX_TEXT2);
//                mEditText2.addTextChangedListener(mPrefixTextWatcher);
                addPrefixTextChangedListener(mEditText2, PRE_FIX_TEXT2);
                break;
            case R.id.remove_2_button:
//                mEditText2.removeTextChangedListener(mPrefixTextWatcher);
                removePrefixTextChangedListener(mEditText2);
                break;
        }
    }

    private void addPrefixTextChangedListener(EditText editText, String prefixText) {
        mPrefixTextWatcher.setPrefixText(editText, prefixText);
        editText.addTextChangedListener(mPrefixTextWatcher);
    }

    private void removePrefixTextChangedListener(EditText editText) {
        editText.removeTextChangedListener(mPrefixTextWatcher);
        editText.setText("");
    }

    private class PrefixTextWatcher implements TextWatcher {

        private EditText mEditText;
        private String mPrefixText;

        public void setPrefixText(EditText editText, String prefixText) {
            mEditText = editText;
            mPrefixText = prefixText;
            mEditText.setText(mPrefixText);
            Selection.setSelection(mEditText.getText(), mEditText.getText().length());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!s.toString().contains(mPrefixText)) {
                mEditText.setText(mPrefixText);
                Selection.setSelection(mEditText.getText(), mEditText.getText().length());
            }
        }


    }

}
