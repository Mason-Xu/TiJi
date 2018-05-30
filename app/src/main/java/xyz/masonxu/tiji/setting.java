package xyz.masonxu.tiji;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class setting extends BaseActivity {

    private String cache;
    private TextView tv_setting_cache;

    private boolean login_true;

    private Double BMI;
    private Double WHtR;
    private Double WHR;
    private Double Tizhi;
    private String objectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        SysApplication.getInstance().addActivity(this);

        ImageButton back = (ImageButton) findViewById(R.id.setting_back);
        Button finish = (Button) findViewById(R.id.bt_setting_finish);
        Button exitaccount = (Button) findViewById(R.id.bt_setting_exitaccount);
        Button cleanCache = (Button) findViewById(R.id.bt_setting_cleanCache);
        Button account = (Button) findViewById(R.id.bt_setting_account);
        Button sync_up = (Button) findViewById(R.id.bt_setting_sync_up);
        Button sync_down = (Button) findViewById(R.id.bt_setting_sync_down);


        tv_setting_cache = (TextView) findViewById(R.id.tv_setting_cache);
        final TextView tv_setting_BMI = (TextView) findViewById(R.id.tv_setting_BMI);
        final TextView tv_setting_WHtR = (TextView) findViewById(R.id.tv_setting_WHtR);
        final TextView tv_setting_WHR = (TextView) findViewById(R.id.tv_setting_WHR);
        final TextView tv_setting_Tizhi = (TextView) findViewById(R.id.tv_setting_Tizhi);
        final TextView tv_setting_sync_up = (TextView) findViewById(R.id.tv_setting_sync_up);
        final TextView tv_setting_sync_down = (TextView) findViewById(R.id.tv_setting_sync_down);

        List<Body> bodies = DataSupport.where("id=1").find(Body.class);

        BMI = bodies.get(0).getBMI();
        WHtR = bodies.get(0).getWHtR();
        WHR = bodies.get(0).getWHR();
        Tizhi = bodies.get(0).getTizhi();

        if (BMI != null) {
            tv_setting_BMI.setText(BMI.toString());
        }
        if (BMI != null) {
            tv_setting_WHtR.setText(WHtR.toString());
        }
        if (BMI != null) {
            tv_setting_WHR.setText(WHR.toString());
        }
        if (BMI != null) {
            tv_setting_Tizhi.setText(Tizhi.toString() + "%");
        }


        //get username from bmob and set text for username_textview
        TextView username_textview = (TextView) findViewById(R.id.tv_setting_account);
        String username = (String) MyUser.getObjectByKey("username");
        objectId = (String) MyUser.getObjectByKey("objectId");
        if (username == null) {
            username_textview.setText("未登录");
            login_true = false;
        } else {
            username_textview.setText("已登录,用户名: " + username);
            login_true = true;
        }


        try {
            tv_setting_cache.setText(DataCleanManager.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(setting.this, MainActivity.class));
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SysApplication.getInstance().exit();
            }
        });

        exitaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUser.logOut();   //清除缓存用户对象
                MyUser currentUser = (MyUser) MyUser.getCurrentUser(); // 现在的currentUser是null了
                refresh();
            }
        });

        cleanCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(setting.this, "清理完成", Toast.LENGTH_SHORT).show();
                DataCleanManager.clearAllCache(getApplicationContext());
                try {
                    tv_setting_cache.setText(DataCleanManager.getTotalCacheSize(getApplicationContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
                if (!login_true) {
                    startActivity(new Intent(setting.this, LoginActivity.class));
                } else {
                    toast("已登录");
                }


            }
        });

        sync_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyUser myUser = new MyUser();
                myUser.setBMI(BMI);
                myUser.setWHtR(WHtR);
                myUser.setWHR(WHR);
                myUser.setTizhi(Tizhi);
                myUser.update(objectId, new UpdateListener() {

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("bmob", "上传成功");
                            tv_setting_sync_up.setText("上传成功");
                        } else {
                            Log.i("bmob", "上传失败：" + e.getMessage() + "," + e.getErrorCode());
                            tv_setting_sync_up.setText("上传失败");
                        }
                    }
                });
            }
        });

        sync_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BmobQuery<MyUser> query = new BmobQuery<MyUser>();
                query.getObject(objectId, new QueryListener<MyUser>() {

                    @Override
                    public void done(MyUser object, BmobException e) {
                        if (e == null) {

                            tv_setting_BMI.setText(object.getBMI().toString());

                            tv_setting_WHtR.setText(object.getWHtR().toString());
                            tv_setting_WHR.setText(object.getWHR().toString());
                            tv_setting_Tizhi.setText(object.getTizhi().toString()+"%");
                            //获得createdAt数据创建时间（注意是：createdAt，不是createAt）

                            tv_setting_sync_down.setText("更新时间:" + object.getCreatedAt().toString());

                        } else {
                            Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                            toast("下载数据失败");
                        }
                    }

                });


            }
        });
    }

    public void refresh() {
        onCreate(null);
    }

}


