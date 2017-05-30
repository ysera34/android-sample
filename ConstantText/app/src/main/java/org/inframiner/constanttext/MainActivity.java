package org.inframiner.constanttext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String PRE_FIX_TEXT = "http://";
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText = (EditText) findViewById(R.id.edit_text);
//        mEditText.setText(PRE_FIX_TEXT);
//        Selection.setSelection(mEditText.getText(), mEditText.getText().length());



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
        mEditText.addTextChangedListener(new PrefixTextWatcher(mEditText, PRE_FIX_TEXT) {});


    }

    private abstract class PrefixTextWatcher implements TextWatcher {

        private EditText mEditText;
        private String mPrefixText;

        public PrefixTextWatcher(EditText editText, String prefixText) {
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
