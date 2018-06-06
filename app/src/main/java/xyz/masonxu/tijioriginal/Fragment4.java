package xyz.masonxu.tijioriginal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class Fragment4 extends Fragment {

    private Double Tizhi;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private Integer sexTemp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment4, container, false);
        final EditText et_tizhi_height = (EditText) view.findViewById(R.id.et_tizhi_height);
        final EditText et_tizhi_weight = (EditText) view.findViewById(R.id.et_tizhi_weight);
        final EditText et_tizhi_age= (EditText) view.findViewById(R.id.et_tizhi_age);
        RadioGroup sexRadioGroup = (RadioGroup) view.findViewById(R.id.rg_tizhi_sexRadio);
        maleRadioButton = (RadioButton) view.findViewById(R.id.rb_tizhi_male);
        femaleRadioButton = (RadioButton) view.findViewById(R.id.rb_tizhi_female);

        sexRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListenerImpl());

        Button bt_calculate_Tizhi = (Button) view.findViewById(R.id.bt_calculate_Tizhi);
        bt_calculate_Tizhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double age = Double.parseDouble(String.valueOf(et_tizhi_age.getText()));
                Double height = Double.parseDouble(String.valueOf(et_tizhi_height.getText()));
                Double weight = Double.parseDouble(String.valueOf(et_tizhi_weight.getText()));
                Tizhi = (1.2*weight/height/height*10000)+(0.23*age-5.4)-(10.8*sexTemp);
                Intent intent = new Intent();
                intent.setClass(getActivity(), Calculate4.class);
                // 传递参数
                intent.putExtra("Tizhi",Tizhi);
                intent.putExtra("sex", sexTemp);
                startActivityForResult(intent, 0);
            }
        });
        return view;
    }

    /**
     * RadioGroup 选择监听事件
     */
    private class OnCheckedChangeListenerImpl implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            sexTemp = 1;
            if (maleRadioButton.getId() == checkedId) {
                sexTemp = 1;
            }
            if (femaleRadioButton.getId() == checkedId) {
                sexTemp = 0;   //取得单选文本
            }
        }
    }

}
