package com.tcl.systemmonitor;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {


    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mDatas;
    private TextView tv_cpu, tv_zram, tv_process;

    /*  private BadgeView mBadgeView1;
        private BadgeView mBadgeView2;*/
    private LinearLayout mLinearLayout_Zram;
    private LinearLayout mLinearLayout_Process;


    private int mScreen1_3;
    private ImageView mTabline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        getActionBar().setDisplayShowHomeEnabled(false);

        //  getActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("----");
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        tv_cpu = (TextView) findViewById(R.id.tv_CPU);
        tv_cpu.setOnClickListener(this);
        tv_zram = (TextView) findViewById(R.id.tv_Zram);
        tv_zram.setOnClickListener(this);
        tv_process = (TextView) findViewById(R.id.tv_Process);
        tv_process.setOnClickListener(this);

        mLinearLayout_Zram = (LinearLayout) findViewById(R.id.Lin_out_Z);
        mLinearLayout_Zram = (LinearLayout) findViewById(R.id.Lin_out_P);

        mDatas = new ArrayList<Fragment>();
        initTabLine();

        initView();
    }


    //实现三个Tab的加入，并加入滑动的方法
    private void initView() {
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setAdapter(new SystemAdapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                LinearLayout.LayoutParams lp = (LayoutParams) mTabline.getLayoutParams();
                lp.leftMargin = (int) (i * mScreen1_3 + v * mScreen1_3);
                mTabline.setLayoutParams(lp);

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                    mType =0;
                        invalidateOptionsMenu();

                        Toast.makeText(MainActivity.this, i + "", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        mType =1;
                        invalidateOptionsMenu();

                        Toast.makeText(MainActivity.this,  i + "", Toast.LENGTH_SHORT).show();

                        break;
                    case 2:
                        mType =2;
                        invalidateOptionsMenu();

                        Toast.makeText(MainActivity.this, i+"", Toast.LENGTH_SHORT).show();

                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }

        });

    }


    public class SystemAdapter extends FragmentPagerAdapter {

        @SuppressLint("UseSparseArrays")
        HashMap<Integer, Fragment> mFragment = new HashMap<Integer, Fragment>();

        public SystemAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int i) {
            if (mFragment.containsKey(i))
                return mFragment.get(i);

            switch (i) {
                case 0:
                    mFragment.put(0, new CpuFragment());
                    break;
                case 1:
                    mFragment.put(1, new ZramFragment());
                    break;
                case 2:
                    mFragment.put(2, new ProcessFragment());
                    break;
                case 3:
                    // under construction
                    break;
            }
            return mFragment.get(i);
        }


        public int getCount() {
            return 3;

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_CPU:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tv_Zram:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.tv_Process:
                mViewPager.setCurrentItem(2);
                break;
        }
    }

    private void initTabLine() {

        mTabline = (ImageView) findViewById(R.id.image_tabline);
        Display display = getWindow().getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        mScreen1_3 = outMetrics.widthPixels / 3;
        LayoutParams lp = (LayoutParams) mTabline.getLayoutParams();
        lp.width = mScreen1_3;
        mTabline.setLayoutParams(lp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.style1, menu);
        return true;
    }
    private int mType ;

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.d("ll", "onPrepareOptionsMenu ");
         menu.clear();
        Log.d("ll", "onPrepareOptionsMenu "+mType);
        MenuInflater inflater = this.getMenuInflater();
        switch (mType){
            case 0:
                inflater.inflate(R.menu.style1, menu);
                break;
            case 1:
                inflater.inflate(R.menu.style2, menu);
                break;
            case 2:
                inflater.inflate(R.menu.style3, menu);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()){
        case R.id.action_item1:
            Toast.makeText(MainActivity.this, "你点了第一个", Toast.LENGTH_SHORT).show();
        break;
        case  R.id.ui_menu_help:
            Toast.makeText(MainActivity.this, "help", Toast.LENGTH_SHORT).show();

            break;
        case R.id.ui_menu_setting:
            Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();

            break;
        case R.id.ui_menu_ss:
            Toast.makeText(MainActivity.this, "ss", Toast.LENGTH_SHORT).show();

            break;

    }

        return super.onOptionsItemSelected(item);
    }
}
