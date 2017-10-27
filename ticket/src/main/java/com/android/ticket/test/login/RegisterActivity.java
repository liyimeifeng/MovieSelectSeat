package com.android.ticket.test.login;

import com.android.test.info.LoginInfo;
import com.android.test.info.handle.LoginInfoHandle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    EditText edittext[];
    private static final int EditTextIDs[] = {R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4, R.id.editText5,};
    private static final int Prompt_Str[] = {R.string.prompt_info1, R.string.prompt_info2, R.string.prompt_info3, R.string.prompt_info4, R.string.prompt_info5,};
    Button b_register = null;
    Button b_cancal = null;
    Context context = null;

    //Spinner sp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regit);
        context = this;
        b_register = (Button) findViewById(R.id.btn_Register);
        b_cancal = (Button) findViewById(R.id.btn_RegisterCancel);
        Init();
        button_handle();
    }

    public void Init() {
        edittext = new EditText[EditTextIDs.length];

        for (int i = 0; i < edittext.length; i++) {
            edittext[i] = (EditText) findViewById(EditTextIDs[i]);
        }
        edittext[2].setOnFocusChangeListener(new repwdOnFocusChangeListener());
    }

    public void button_handle() {

        b_register.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (submit()) {
                    Intent it = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(it);
                    RegisterActivity.this.finish();
                }
            }
        });

        b_cancal.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(it);
                RegisterActivity.this.finish();
            }
        });
    }

    public boolean submit() {
        for (int i = 0; i < edittext.length; i++) {
            if (edittext[i].getText().toString().length() == 0) {
                Toast.makeText(RegisterActivity.this, getString(Prompt_Str[i]), Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        LoginInfo logininfo = new LoginInfo();
        logininfo.setId(edittext[0].getText().toString());
        logininfo.setPwd(edittext[1].getText().toString());
        logininfo.settel(edittext[3].getText().toString());
        logininfo.setCard(edittext[4].getText().toString());
        LoginInfoHandle handle = new LoginInfoHandle(this);
        handle.createDatabase();
        if (handle.isExist(logininfo.getId())) {
            Toast.makeText(RegisterActivity.this, "账户名已经存在,请重新输入", Toast.LENGTH_SHORT).show();
            return true;
        }
        handle.insert(logininfo);
        handle.upgrateDatabase();
        return true;
    }

    class repwdOnFocusChangeListener implements OnFocusChangeListener {
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus == false) {
                if (!(edittext[1].getText().toString().equals(edittext[2].getText().toString()))) {
                    Toast.makeText(RegisterActivity.this, getString(R.string.pwd_no_same), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
