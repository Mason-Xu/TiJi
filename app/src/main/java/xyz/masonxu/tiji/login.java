package xyz.masonxu.tiji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        SysApplication.getInstance().addActivity(this);

        EditText et_username = (EditText)findViewById(R.id.et_login_username);
        EditText et_password = (EditText)findViewById(R.id.et_login_pwd);
        String username = String.valueOf(et_username.getText());
        String password = String.valueOf(et_password.getText());

        ImageButton back = (ImageButton) findViewById(R.id.login_back);
        Button login = (Button)findViewById(R.id.bt_login_Login);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

}
