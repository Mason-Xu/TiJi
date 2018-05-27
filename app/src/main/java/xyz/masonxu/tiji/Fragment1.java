package xyz.masonxu.tiji;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class Fragment1 extends Fragment {

    private Double BMI;
    private  View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment1,container,false);
        final EditText et_bmi_height = (EditText)view.findViewById(R.id.et_bmi_height);
        final EditText et_bmi_weight = (EditText)view.findViewById(R.id.et_bmi_weight);

        Button bt_calculate_BMI = (Button)view.findViewById(R.id.bt_calculate_BMI);
        bt_calculate_BMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double weight = Double.parseDouble(String.valueOf(et_bmi_weight.getText()));
                Integer height = Integer.parseInt(String.valueOf(et_bmi_height.getText()));
                BMI = (weight/height/height*10000);
                Intent intent = new Intent();
                intent.setClass(getActivity(), Calculate1.class);
                // 传递参数
                intent.putExtra("BMI",BMI);
                startActivityForResult(intent, 0);
            }
        });
        return view;
    }


}
