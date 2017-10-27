package com.android.ticket.test.login;

import com.android.test.info.handle.LoginInfoHandle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    protected static final String REQUEST_TYPE_A = null;
    /**
     * Called when the activity is first created.
     */
    EditText ET_username = null;
    EditText ET_password = null;
    Button BN_login = null;
    Button regist_button = null;
    CheckBox cb = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ET_username = (EditText) findViewById(R.id.loginame);
        ET_password = (EditText) findViewById(R.id.password1);
        BN_login = (Button) findViewById(R.id.btn_Ok);
        regist_button = (Button) findViewById(R.id.zhuce);
        cb = (CheckBox)findViewById(R.id.cbRemember) ;
        checkIfRemember();
        ButtonClickListener();
        registration_handle();  //注册
    }

    private void ButtonClickListener() {
        BN_login.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String username = ET_username.getText().toString().trim();
                String pwd = ET_password.getText().toString().trim();
                LoginInfoHandle handle = new LoginInfoHandle(LoginActivity.this);
                handle.createDatabase();    //创建数据库
                if (validate()) {
                    if (cb.isChecked()) {
                        rememberMe(username, pwd);
                    }
                    if (ET_username.getText().toString().equals("admin") && ET_password.getText().toString().equals("admin")) {
                        Gotomainmenu1();
                    } else if (handle.CorrectOfAccountAndPassword(ET_username.getText().toString(), ET_password.getText().toString())) {
                        Gotomainmenu();
                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.user_or_pwd_err), Toast.LENGTH_SHORT).show();
                    }

                }
            }

            public boolean validate() {
                String username = ET_username.getText().toString().trim();
                if (username.equals("")) {
                    Toast.makeText(LoginActivity.this, getString(R.string.usernamenowrite), Toast.LENGTH_SHORT).show();
                    return false;
                }
                String pwd = ET_password.getText().toString().trim();
                if (pwd.equals("")) {
                    Toast.makeText(LoginActivity.this, getString(R.string.pwdnowrite), Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }

            /**
             * 去往购票页面
             */
            private void Gotomainmenu() {
                String username = ET_username.getText().toString().trim();
                Intent it = new Intent();
                it.putExtra("username", username);
                it.setClass(LoginActivity.this, MainmenuActivity.class);
                startActivity(it);
                LoginActivity.this.finish();
            }

            /**
             * 去往管理员页面
             */
            private void Gotomainmenu1() {
                String username = ET_username.getText().toString().trim();
                Intent it = new Intent();
                it.putExtra("username", username);
                it.setClass(LoginActivity.this, MainmenuActivity1.class);
                startActivity(it);
                LoginActivity.this.finish();
            }
        });
    }

    public void registration_handle() {
        regist_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it);
                LoginActivity.this.finish();
            }
        });
    }

    public void rememberMe(String uid, String pwd) {
        SharedPreferences sp = getPreferences(MODE_PRIVATE);    //获得Preferences
        SharedPreferences.Editor editor = sp.edit();            //获得Editor
        editor.putString("uid", uid);                            //将用户名存入Preferences
        editor.putString("pwd", pwd);                            //将密码存入Preferences
        editor.commit();
    }

    //方法：从Preferences中读取用户名和密码
    public void checkIfRemember() {
        SharedPreferences sp = getPreferences(MODE_PRIVATE);    //获得Preferences
        String uid = sp.getString("uid", null);
        String pwd = sp.getString("pwd", null);
        if (uid != null && pwd != null) {
            ET_username.setText(uid);
            ET_password.setText(pwd);
            cb.setChecked(true);
        }
    }
}