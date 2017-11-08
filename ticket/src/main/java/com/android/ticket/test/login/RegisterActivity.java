package com.android.ticket.test.login;

import com.android.test.info.DBManager;
import com.android.test.info.LoginInfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.security.auth.login.LoginException;

public class RegisterActivity extends Activity {
    private final static String TAG = RegisterActivity.class.getSimpleName();
    EditText edittext[];
    private static final int EditTextIDs[] = {R.id.account_Text, R.id.pwd_Text, R.id.pwdcomfirm_Text, R.id.phone_Text, R.id.email_Text
            ,};
    private static final int Prompt_Str[] = {R.string.prompt_info1, R.string.prompt_info2, R.string.prompt_info3, R.string.prompt_info4, R.string.prompt_info5,};
    Button b_register = null;
    Button b_cancal = null;
    Context context = null;
    private DBManager mDbmanager;

    //Spinner sp1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regit);
        context = this;
        mDbmanager = DBManager.getInstance(this);
        b_register = (Button) findViewById(R.id.btn_Register);
        b_cancal = (Button) findViewById(R.id.btn_RegisterCancel);
        Init();
        submit();
        button_handle();
    }

    public void Init() {
        edittext = new EditText[EditTextIDs.length];

        for (int i = 0; i < edittext.length; i++) {
            edittext[i] = (EditText) findViewById(EditTextIDs[i]);
        }
        edittext[2].setOnFocusChangeListener(new repwdOnFocusChangeListener());
        edittext[0].setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus){   //失去焦点时
                    if (mDbmanager.isAccountExist(edittext[0].getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "账号已存在，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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
//        LoginInfo logininfo = new LoginInfo();
//        logininfo.setId(edittext[0].getText().toString());
//        logininfo.setPwd(edittext[1].getText().toString());
//        logininfo.setTel(edittext[3].getText().toString());
//        logininfo.setEmail(edittext[4].getText().toString());
//        LoginInfoHandle handle = new LoginInfoHandle(this);
//        handle.createDatabase();
//        if (handle.isExist(logininfo.getId())) {
//            return true;
//        }
//        handle.insert(logininfo);
//        handle.upgrateDatabase();

                    LoginInfo user = new LoginInfo();
                    user.setId(edittext[0].getText().toString());
                    user.setEmail(edittext[4].getText().toString());
                    user.setPwd(edittext[1].getText().toString());
                    user.setTel(edittext[3].getText().toString());
                    mDbmanager.insertLoginInfo(user);
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
