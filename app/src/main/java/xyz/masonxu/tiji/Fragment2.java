package xyz.masonxu.tiji;

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
public class Fragment2 extends Fragment {

    private Double WHTR;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private Integer sexTemp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        final EditText et_whtr_height = (EditText)view.findViewById(R.id.et_whtr_height);
        final EditText et_whtr_yaowei = (EditText)view.findViewById(R.id.et_whtr_yaowei);
        RadioGroup sexRadioGroup = (RadioGroup)view.findViewById(R.id.rg_whtr_sexRadio);
        maleRadioButton = (RadioButton)view.findViewById(R.id.rb_whtr_male);
        femaleRadioButton = (RadioButton)view.findViewById(R.id.rb_whtr_female);

        sexRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListenerImpl());

        Button bt_calculate_WHTR = (Button)view.findViewById(R.id.bt_calculate_WHTR);
        bt_calculate_WHTR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double yaowei = Double.parseDouble(String.valueOf(et_whtr_yaowei.getText()));
                Integer height = Integer.parseInt(String.valueOf(et_whtr_height.getText()));
                WHTR = (yaowei/height);
                Intent intent = new Intent();
                intent.setClass(getActivity(), Calculate2.class);
                // 传递参数
                intent.putExtra("WHTR",WHTR);
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
            if(Fragment2.this.maleRadioButton.getId() == checkedId){
                sexTemp = 1;
            }
            if(Fragment2.this.femaleRadioButton.getId() == checkedId){
                sexTemp = 0;   //取得单选文本
            }
        }
    }


}
