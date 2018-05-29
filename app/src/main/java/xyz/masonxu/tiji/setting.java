package xyz.masonxu.tiji;

import android.app.Application;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.litepal.crud.DataSupport;
import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

public class setting extends AppCompatActivity {

    private String cache;
    private TextView tv_setting_cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        SysApplication.getInstance().addActivity(this);

        ImageButton back = (ImageButton) findViewById(R.id.setting_back);
        Button finish = (Button) findViewById(R.id.bt_setting_finish);
        Button cleanCache = (Button) findViewById(R.id.bt_setting_cleanCache);
        tv_setting_cache = (TextView) findViewById(R.id.tv_setting_cache);


        TextView tv_setting_BMI = (TextView) findViewById(R.id.tv_setting_BMI);
        TextView tv_setting_WHtR = (TextView) findViewById(R.id.tv_setting_WHtR);
        TextView tv_setting_WHR = (TextView) findViewById(R.id.tv_setting_WHR);
        TextView tv_setting_Tizhi = (TextView) findViewById(R.id.tv_setting_Tizhi);

        List<Body> bodies = DataSupport.where("id=1").find(Body.class);

        Double BMI = bodies.get(0).getBMI();
        Double WHtR = bodies.get(0).getWHtR();
        Double WHR = bodies.get(0).getWHR();
        Double Tizhi = bodies.get(0).getTizhi();

        if (BMI != null) {
            tv_setting_BMI.setText(BMI.toString());
        }
        if (BMI != null) {
            tv_setting_WHtR.setText(WHtR.toString());
        }
        if (BMI != null) {
            tv_setting_WHR.setText(WHR.toString());
        }
        if (BMI != null) {
            tv_setting_Tizhi.setText(Tizhi.toString()+"%");
        }


        try {
            tv_setting_cache.setText(DataCleanManager.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }


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

        cleanCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(setting.this, "清理完成", Toast.LENGTH_SHORT).show();
                DataCleanManager.clearAllCache(getApplicationContext());
                try {
                    tv_setting_cache.setText(DataCleanManager.getTotalCacheSize(getApplicationContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

}
