package xyz.masonxu.tijioriginal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

public class Calculate5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_frag5);
        SysApplication.getInstance().addActivity(this);

        TextView tv_best_weight = (TextView) findViewById(R.id.tv_best_weight);
        TextView tv_best_shangwei = (TextView) findViewById(R.id.tv_best_shangwei);
        TextView tv_best_yaowei = (TextView) findViewById(R.id.tv_best_yaowei);
        TextView tv_best_fuwei = (TextView) findViewById(R.id.tv_best_fuwei);
        TextView tv_best_tunwei = (TextView) findViewById(R.id.tv_best_tunwei);
        TextView tv_best_thigh = (TextView) findViewById(R.id.tv_best_thigh);
        ImageButton back = (ImageButton) findViewById(R.id.calculate_best_back);
        Double height = this.getIntent().getExtras().getDouble("height");

        Double weight = (height-100)*0.9;
        Double shangwei = height*0.515;
        Double yaowei = height*0.37;
        Double fuwei = height*0.457;
        Double tunwei = height*0.542;
        Double thigh = height*0.26+7.8;
//        // 四舍五入保留一位小数
//        BigDecimal b = new BigDecimal(tv_best_weight);
//        Double BMI_format = b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
//        tv_best_weight.setText(BMI_format.toString());
        tv_best_weight.setText(weight.toString());
        tv_best_shangwei.setText(shangwei.toString());
        tv_best_yaowei.setText(yaowei.toString());
        tv_best_fuwei.setText(fuwei.toString());
        tv_best_tunwei.setText(tunwei.toString());
        tv_best_thigh.setText(thigh.toString());



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
