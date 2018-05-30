package xyz.masonxu.tiji;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class ForgetActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton button_back;
    private Button button_getauthcode;
    private Button button_commit;
    private EditText et_phonenumber;
    private EditText et_authcode;
    private EditText et_password1;
    private EditText et_password2;
    private String authcode;
    private String phonenumber;
    private String inputpassword;
    private String confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget);
        SysApplication.getInstance().addActivity(this);

        button_back = (ImageButton) findViewById(R.id.forget_back);
        button_getauthcode = (Button) findViewById(R.id.bt_forget_getauthcode);
        button_commit = (Button) findViewById(R.id.bt_forget_Submit);
        et_phonenumber = (EditText) findViewById(R.id.et_forget_phonenumber);
        et_authcode = (EditText) findViewById(R.id.et_forget_authcode);
        et_password1= (EditText) findViewById(R.id.et_forget_pwd);
        et_password2 = (EditText) findViewById(R.id.et_forget_pwd2);

        initView();
    }

    private void initView() {
        button_back.setOnClickListener(this);
        button_getauthcode.setOnClickListener(this);
        button_commit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forget_back:
                startActivity(new Intent(ForgetActivity.this,setting.class));
                break;
            case R.id.bt_forget_getauthcode:
                sendAuthcode();
                break;
            case R.id.bt_forget_Submit:
                Commit();
                break;

            default:
                break;

        }

    }

    private void sendAuthcode() {
        // 发送短信验证码
        phonenumber = et_phonenumber.getText().toString();
        BmobSMS.requestSMSCode(phonenumber, "舟一走忘记密码验证码",new QueryListener<Integer>() {

            @Override
            public void done(Integer smsId,BmobException ex) {
                if(ex==null){//验证码发送成功
                    Log.i("smile", "短信id："+smsId);//用于查询本次短信发送详情
                }
            }
        });
    }

    private void Commit() {
        phonenumber = et_phonenumber.getText().toString();
        authcode = et_authcode.getText().toString();
        //验证验证码和密码是否符合条件
        inputpassword=et_password1.getText().toString();
        confirmpassword= et_password2.getText().toString();
        if (inputpassword.equals(confirmpassword)){
            BmobUser.resetPasswordBySMSCode(authcode,inputpassword, new UpdateListener() {

                @Override
                public void done(BmobException ex) {
                    if(ex==null){
                        Log.i("smile", "密码重置成功");
                        toast("密码重置成功");

                    }else{
                        Log.i("smile", "重置失败：code ="+ex.getErrorCode()+",msg = "+ex.getLocalizedMessage());
                        toast("获取有些不正确呢");
                    }
                }
            });
        }else {
            toast("验证码不正确或密码两次不相同");
        }


    }
}
