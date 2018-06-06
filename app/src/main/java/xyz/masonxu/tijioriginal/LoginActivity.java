package xyz.masonxu.tijioriginal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends BaseActivity {

    private EditText username_edit;
    private EditText password_edit;
    private Button login_button;
    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        SysApplication.getInstance().addActivity(this);

        Button bt_forgetpassword = (Button) findViewById(R.id.bt_login_Forget);
        Button bt_regist = (Button) findViewById(R.id.bt_login_Regist);
        bt_forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });
        bt_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // 以下是登录验证


        username_edit = (EditText) findViewById(R.id.et_login_username);
        password_edit = (EditText) findViewById(R.id.et_login_pwd);


        login_button = (Button) findViewById(R.id.bt_login_Login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUser user = new MyUser();
                username = username_edit.getText().toString();
                password = password_edit.getText().toString();
                user.setUsername(username);
                user.setPassword(password);
                user.login(new SaveListener<MyUser>() {

                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e == null) {
                            toast("登录成功");
                            //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                            //跳转主页面
                            startActivity(new Intent(LoginActivity.this,setting.class));
                        } else {
                            toast("用户名或密码错误");
                            loge(e);
                        }
                    }

//                    @Override
//                    public void done(BmobUser bmobUser, BmobException e) {
//                        if (e == null) {
//                            toast("登录成功");
//                            //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
//                            //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
//                            //跳转主页面
//                            startActivity(new Intent(LoginActivity.this,setting.class));
//                        } else {
//                            toast("用户名或密码错误");
//                            loge(e);
//                        }
//                    }
                });
            }
        });

        // 返回按钮
        ImageButton back = (ImageButton) findViewById(R.id.login_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, setting.class));
            }
        });


    }

}
