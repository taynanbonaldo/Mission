package mesaecadeira.com.missao;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CodeActivity extends BaseActivity implements View.OnClickListener  {

    Button buttonAccess;
    EditText editTextCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);

        buttonAccess = (Button) findViewById(R.id.button_enter);
        editTextCode = (EditText) findViewById(R.id.edittext_code);

        editTextCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    validateCode();
                }
                return false;
            }
        });

        buttonAccess.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        validateCode();
    }

    private void validateCode(){
        String code = editTextCode.getText().toString();

        if(code.length() == 0
                || !code.equals(getString(R.string.code))){

            createAndShowAttentionDialogWithCallback(getString(R.string.message_invalid_code), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
                    imm.showSoftInput(editTextCode, InputMethodManager.SHOW_IMPLICIT);
                }
            });
        } else{
            startActivity(new Intent(this, MissionActivity.class));
        }
    }
}
