package xyz.masonxu.tiji;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        SysApplication.getInstance().addActivity(this);

        ImageButton back = (ImageButton) findViewById(R.id.setting_back);
        Button finish = (Button)findViewById(R.id.bt_setting_finish);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SysApplication.getInstance().exit();
            }
        });

    }

}
