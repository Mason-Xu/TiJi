package xyz.masonxu.tiji;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class Calculate1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_frag1);
        SysApplication.getInstance().addActivity(this);
        TextView tv_bmi_BMI = (TextView) findViewById(R.id.tv_bmi_BMI);
        TextView tv_bmi_health = (TextView) findViewById(R.id.tv_bmi_health);
        ImageView img_bmi_health = (ImageView)findViewById(R.id.img_bmi_health);
        ImageButton back = (ImageButton) findViewById(R.id.calculate_bmi_back);
        Double BMI = this.getIntent().getExtras().getDouble("BMI");

        // 四舍五入保留一位小数
        BigDecimal b = new BigDecimal(BMI);
        Double BMI_format = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
        tv_bmi_BMI.setText(BMI_format.toString());

        Body body = new Body();
        body.setBMI(BMI_format);
        body.updateAll("id=1");

        if(BMI < 18.5){
            tv_bmi_health.setText("太轻了");
            img_bmi_health.setImageResource(R.drawable.img_health);
        }else if(BMI >= 18.5 && BMI < 23.9){
            tv_bmi_health.setText("正常");
            img_bmi_health.setImageResource(R.drawable.img_health);
        }else if(BMI >= 23.9 && BMI < 27){
            tv_bmi_health.setText("有点重");
            img_bmi_health.setImageResource(R.drawable.img_warning);
        }else if(BMI >= 27 && BMI <= 30){
            tv_bmi_health.setText("肥胖");
            img_bmi_health.setImageResource(R.drawable.img_warning);
        }else if(BMI > 30){
            tv_bmi_health.setText("非常肥胖");
            img_bmi_health.setImageResource(R.drawable.img_dangerous);
        }

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
