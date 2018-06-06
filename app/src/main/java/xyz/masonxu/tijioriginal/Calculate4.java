package xyz.masonxu.tijioriginal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

public class Calculate4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_frag4);
        SysApplication.getInstance().addActivity(this);

        TextView tv_tizhi_Tizhi = (TextView) findViewById(R.id.tv_tizhi_Tizhi);
        TextView tv_tizhi_health = (TextView) findViewById(R.id.tv_tizhi_health);
        ImageView img_tizhi_health = (ImageView) findViewById(R.id.img_tizhi_health);
        ImageButton back = (ImageButton) findViewById(R.id.calculate_tizhi_back);
        // 获取参数
        Double Tizhi = this.getIntent().getExtras().getDouble("Tizhi");
        Integer sex = this.getIntent().getExtras().getInt("sex");

        // 四舍五入保留一位小数
        BigDecimal b = new BigDecimal(Tizhi);
        Double Tizhi_format = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        tv_tizhi_Tizhi.setText(Tizhi_format.toString());

        Body body = new Body();
        body.setTizhi(Tizhi_format);
        body.updateAll("id=1");

        if (sex==1) {
            if (Tizhi >=10 && Tizhi<=20) {
                tv_tizhi_health.setText("正常");
                img_tizhi_health.setImageResource(R.drawable.img_health);
            } else if(Tizhi < 10 ){
                tv_tizhi_health.setText("瘦");
                img_tizhi_health.setImageResource(R.drawable.img_warning);
            } else if(Tizhi > 20 && Tizhi <= 28 ){
                tv_tizhi_health.setText("轻微胖");
                img_tizhi_health.setImageResource(R.drawable.img_warning);
            }else if(Tizhi > 28 ){
                tv_tizhi_health.setText("非常肥胖");
                img_tizhi_health.setImageResource(R.drawable.img_dangerous);
            }
        } else if (sex==0) {
            if (Tizhi >=20 && Tizhi<=35) {
                tv_tizhi_health.setText("正常");
                img_tizhi_health.setImageResource(R.drawable.img_health);
            } else if(Tizhi < 20 ){
                tv_tizhi_health.setText("瘦");
                img_tizhi_health.setImageResource(R.drawable.img_warning);
            } else if(Tizhi > 35 && Tizhi <= 40 ){
                tv_tizhi_health.setText("轻微胖");
                img_tizhi_health.setImageResource(R.drawable.img_warning);
            }else if(Tizhi > 40 ){
                tv_tizhi_health.setText("非常肥胖");
                img_tizhi_health.setImageResource(R.drawable.img_dangerous);
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
