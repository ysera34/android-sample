package org.inframiner.constanttext;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by yoon on 2017. 5. 30..
 */

public abstract class PrefixTextListener implements TextWatcher {

    private EditText mEditText;
    private String mPrefixText;

    public abstract void addPrefixTextChangedListener();
    public abstract void removePrefixTextChangedListener();

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
