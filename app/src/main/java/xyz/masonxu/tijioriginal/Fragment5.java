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

/**
 * Created by Carson_Ho on 16/7/22.
 */
public class Fragment5 extends Fragment {

    private Double height;
    private  View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment5,container,false);
        final EditText et_best_height = (EditText)view.findViewById(R.id.et_best_height);

        Button bt_calculate_best = (Button)view.findViewById(R.id.bt_calculate_Best);
        bt_calculate_best.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double height = Double.parseDouble(String.valueOf(et_best_height.getText()));
                Intent intent = new Intent();
                intent.setClass(getActivity(), Calculate5.class);
                // 传递参数
                intent.putExtra("height",height);
                startActivityForResult(intent, 0);
            }
        });
        return view;
    }

}
