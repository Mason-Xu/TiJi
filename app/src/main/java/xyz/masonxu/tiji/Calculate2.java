package xyz.masonxu.tiji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.math.BigDecimal;

public class Calculate2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_frag2);
        SysApplication.getInstance().addActivity(this);

        TextView tv_whtr_WHTR = (TextView) findViewById(R.id.tv_whtr_WHTR);
        TextView tv_whtr_health = (TextView) findViewById(R.id.tv_whtr_health);
        ImageView img_whtr_health = (ImageView) findViewById(R.id.img_whtr_health);
        ImageButton back = (ImageButton) findViewById(R.id.calculate_whtr_back);
        // 获取参数
        Double WHTR = this.getIntent().getExtras().getDouble("WHTR");
        Integer sex = this.getIntent().getExtras().getInt("sex");

        // 四舍五入保留一位小数
        BigDecimal b = new BigDecimal(WHTR);
        Double WHR_format = b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        tv_whtr_WHTR.setText(WHR_format.toString());

        if (sex==1) {
            if (WHTR < 0.34) {
                tv_whtr_health.setText("非常瘦");
                img_whtr_health.setImageResource(R.drawable.img_warning);
            } else if (WHTR >= 0.35 && WHTR < 0.43) {
                tv_whtr_health.setText("健康的瘦");
                img_whtr_health.setImageResource(R.drawable.img_health);
            } else if (WHTR >= 0.43 && WHTR < 0.53) {
                tv_whtr_health.setText("健康");
                img_whtr_health.setImageResource(R.drawable.img_health);
            } else if (WHTR >= 0.53 && WHTR < 0.58) {
                tv_whtr_health.setText("过重");
                img_whtr_health.setImageResource(R.drawable.img_warning);
            } else if (WHTR >= 0.58 && WHTR < 0.63) {
                tv_whtr_health.setText("严重超重");
                img_whtr_health.setImageResource(R.drawable.img_warning);
            } else if (WHTR > 0.63) {
                tv_whtr_health.setText("病态肥胖");
                img_whtr_health.setImageResource(R.drawable.img_dangerous);
            }
        } else if (sex==0) {
            if (WHTR < 0.34) {
                tv_whtr_health.setText("非常瘦");
                img_whtr_health.setImageResource(R.drawable.img_warning);
            } else if (WHTR >= 0.35 && WHTR < 0.42) {
                tv_whtr_health.setText("健康的瘦");
                img_whtr_health.setImageResource(R.drawable.img_health);
            } else if (WHTR >= 0.42 && WHTR < 0.49) {
                tv_whtr_health.setText("健康");
                img_whtr_health.setImageResource(R.drawable.img_health);
            } else if (WHTR >= 0.49 && WHTR < 0.54) {
                tv_whtr_health.setText("过重");
                img_whtr_health.setImageResource(R.drawable.img_warning);
            } else if (WHTR >= 0.54 && WHTR < 0.58) {
                tv_whtr_health.setText("严重超重");
                img_whtr_health.setImageResource(R.drawable.img_warning);
            } else if (WHTR > 0.58) {
                tv_whtr_health.setText("病态肥胖");
                img_whtr_health.setImageResource(R.drawable.img_dangerous);
            }
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
