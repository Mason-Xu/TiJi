package xyz.masonxu.tijioriginal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.litepal.LitePal;

import cn.bmob.v3.Bmob;


public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    private TabLayout.Tab five;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SysApplication.getInstance().addActivity(this);

        Bmob.initialize(this, "38653897272aff77fc7590558a3a72fb");



        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //初始化数据库
        LitePal.getDatabase();

        Body body = new Body();
        body.setId(1);
        body.save();
        //初始化视图
        initViews();
    }



    private void initViews() {

        //使用适配器将ViewPager与Fragment绑定在一起
        mViewPager= (ViewPager) findViewById(R.id.viewPager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(myFragmentPagerAdapter);

        //将TabLayout与ViewPager绑定在一起
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.setupWithViewPager(mViewPager);

        //指定Tab的位置
        one = mTabLayout.getTabAt(0);
        two = mTabLayout.getTabAt(1);
        three = mTabLayout.getTabAt(2);
        four = mTabLayout.getTabAt(3);
        five = mTabLayout.getTabAt(4);


        //设置Tab的图标
        one.setIcon(R.drawable.ic_bmi2);
        two.setIcon(R.drawable.ic_whtr);
        three.setIcon(R.drawable.ic_whr);
        four.setIcon(R.drawable.ic_tizhi2);
        five.setIcon(R.drawable.ic_shengcai);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, setting.class);
                startActivityForResult(intent, 0);
                break;
            case R.id.help:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, help.class);
                startActivityForResult(intent2, 0);
                break;
            case R.id.about:
                // 使用LayoutInflater来加载dialog布局
                LayoutInflater layoutInflater = LayoutInflater.from(this);
                View nameView = layoutInflater.inflate(R.layout.dialog_about, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        this);

                // 使用setView()方法将布局显示到dialog
                alertDialogBuilder.setView(nameView);


                // 设置Dialog按钮
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("确定",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
                break;

            default:
        }
        return true;
    }





}
