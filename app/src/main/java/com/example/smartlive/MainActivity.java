package com.example.smartlive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smartlive.menu.HomeFragment;
import com.example.smartlive.menu.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private List<Fragment> mFragments;

    private LinearLayout mTab1;
    private LinearLayout mTab2;
    private ImageView iv_home;
    private ImageView iv_mine;
    private TextView tv_home;
    private TextView tv_mine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
        initDatas();
    }

    //初始化控件
    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mTab1 = (LinearLayout) findViewById(R.id.id_tab1);
        mTab2 = (LinearLayout) findViewById(R.id.id_tab2);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_mine = (ImageView) findViewById(R.id.iv_mine);
        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_mine = (TextView) findViewById(R.id.tv_mine);
        selectTab(0);
    }

    //设置Tab的点击事件
    private void initEvents() {
        mTab1.setOnClickListener(this);
        mTab2.setOnClickListener(this);
    }

    //初始化数据
    private void initDatas() {
        mFragments = new ArrayList<>();
        mFragments.add(new HomeFragment());
        mFragments.add(new MineFragment());
        //初始化适配器
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewPager.setCurrentItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void selectTab(int i) {
        //根据点击的Tab设置对应的ImageButton为绿色
        switch (i) {
            case 0:
                iv_home.setImageResource(R.drawable.ic_home_selected);
                tv_home.setTextColor(getResources().getColor(R.color.primary));
                break;
            case 1:
                iv_mine.setImageResource(R.drawable.ic_mine_selected);
                tv_mine.setTextColor(getResources().getColor(R.color.primary));
                break;
        }
        //设置当前点击的Tab所对应的页面
        mViewPager.setCurrentItem(i);
    }

    //将四个ImageButton设置为灰色
    private void resetImgs() {
        iv_home.setImageResource(R.drawable.ic_home);
        iv_mine.setImageResource(R.drawable.ic_mine);
        tv_home.setTextColor(getResources().getColor(R.color.primary_vice));
        tv_mine.setTextColor(getResources().getColor(R.color.primary_vice));
    }

    @Override
    public void onClick(View v) {
        //先将四个ImageButton置为灰色
        resetImgs();

        //根据点击的Tab切换不同的页面及设置对应的ImageButton为绿色
        switch (v.getId()) {
            case R.id.id_tab1:
                selectTab(0);
                break;
            case R.id.id_tab2:
                selectTab(1);
                break;
        }
    }
}