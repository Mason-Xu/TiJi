package xyz.masonxu.tiji;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

public class Calculate3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_frag3);
        TextView tv_whr_WHR = (TextView) findViewById(R.id.tv_tizhi_Tizhi);
        TextView tv_whr_health = (TextView) findViewById(R.id.tv_whr_health);
        ImageView img_whr_health = (ImageView) findViewById(R.id.img_whr_health);
        ImageButton back = (ImageButton) findViewById(R.id.calculate_whr_back);
        // 获取参数
        Double WHR = this.getIntent().getExtras().getDouble("WHR");
        Integer sex = this.getIntent().getExtras().getInt("sex");

        // 四舍五入保留一位小数
        BigDecimal b = new BigDecimal(WHR);
        Double WHR_format = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        tv_whr_WHR.setText(WHR_format.toString());

        if (sex==1) {
            if (WHR == 0.9) {
                tv_whr_health.setText("完美");
                img_whr_health.setImageResource(R.drawable.img_perfect);
            } else if (WHR >= 0.85 && WHR <= 0.95) {
                tv_whr_health.setText("正常");
                img_whr_health.setImageResource(R.drawable.img_health);
            } else{
                tv_whr_health.setText("失调");
                img_whr_health.setImageResource(R.drawable.img_imperfect);
            }
        } else if (sex==0) {
            if (WHR == 0.7) {
                tv_whr_health.setText("完美");
                img_whr_health.setImageResource(R.drawable.img_perfect);
            } else if (WHR >= 0.67 && WHR <= 0.8) {
                tv_whr_health.setText("正常");
                img_whr_health.setImageResource(R.drawable.img_health);
            } else{
                tv_whr_health.setText("失调");
                img_whr_health.setImageResource(R.drawable.img_imperfect);
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
