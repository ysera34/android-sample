package org.inframiner.constanttext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String PRE_FIX_TEXT = "http:// ";
    private static final String PRE_FIX_TEXT2 = "file:// ";
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
        mPrefixTextWatcher = new PrefixTextWatcher();

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

    private void addPrefixTextChangedListener(EditText editText, String prefixText) {
        mPrefixTextWatcher.setPrefixText(editText, prefixText);
        editText.addTextChangedListener(mPrefixTextWatcher);
    }

    private void removePrefixTextChangedListener(EditText editText) {
        editText.removeTextChangedListener(mPrefixTextWatcher);
        editText.setText("");
    }
}
