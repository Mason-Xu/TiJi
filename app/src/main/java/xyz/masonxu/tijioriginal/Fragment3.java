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
public class Fragment3 extends Fragment {

    private Double WHR;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private Integer sexTemp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment3,container,false);
        final EditText et_whr_yaowei = (EditText)view.findViewById(R.id.et_whr_yaowei);
        final EditText et_whr_tunwei = (EditText)view.findViewById(R.id.et_whr_tunwei);
        RadioGroup sexRadioGroup = (RadioGroup)view.findViewById(R.id.rg_whr_sexRadio);
        maleRadioButton = (RadioButton)view.findViewById(R.id.rb_whr_male);
        femaleRadioButton = (RadioButton)view.findViewById(R.id.rb_whr_female);

        sexRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListenerImpl());

        Button bt_calculate_WHR = (Button)view.findViewById(R.id.bt_calculate_WHR);
        bt_calculate_WHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double yaowei = Double.parseDouble(String.valueOf(et_whr_yaowei.getText()));
                Double tunwei = Double.parseDouble(String.valueOf(et_whr_tunwei.getText()));
                WHR = (yaowei/tunwei);
                Intent intent = new Intent();
                intent.setClass(getActivity(), Calculate3.class);
                // 传递参数
                intent.putExtra("WHR",WHR);
                intent.putExtra("sex",sexTemp);
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
