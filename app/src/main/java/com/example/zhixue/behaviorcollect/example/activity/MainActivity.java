package com.example.zhixue.behaviorcollect.example.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhixue.behaviorcollect.R;
import com.example.zhixue.behaviorcollect.example.fragment.BaseViewFragment;
import com.example.zhixue.behaviorcollect.example.fragment.HomeFragment;
import com.example.zhixue.behaviorcollect.example.fragment.MyFragment;
import com.example.zhixue.behaviorcollect.example.fragment.ServiceFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 底部导航栏布局
    private LinearLayout mLlMainTab;
    // Home的Tab
    private LinearLayout mLlMainTabHome;
    // 中间的Tab
    private LinearLayout mLlMainTabProduct;
    // 我的的Tab
    private LinearLayout mLlMainTabMy;

    private List<Fragment> mFragmentsList = new ArrayList<Fragment>();
    private List<Fragment> mCurFragmentsList = new ArrayList<Fragment>();

    // 内容布局
    private FrameLayout conentFrame;
    // 当前的Fragment
    private BaseViewFragment currentFragment;
    public final static int FRAGMENT_TAB_FLAG_HOME = 0;
    public final static int FRAGMENT_TAB_FLAG_PRODUCT = 1;
    public final static int FRAGMENT_TAB_FLAG_MY = 2;
    // 当前的Tab的Tag
    private int mCurrentTabFlagIndex = FRAGMENT_TAB_FLAG_HOME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFragmentsList.add(new HomeFragment());
        mFragmentsList.add(new ServiceFragment());
        mFragmentsList.add(new MyFragment());
        initViewFromXML();
        fillView();
        initListener();

    }

    public void initViewFromXML() {

        // 内容布局
        conentFrame = (FrameLayout) findViewById(R.id.fl_activity_main_viewpager);
        // 主页Tab:借款
        mLlMainTabHome = (LinearLayout) this.findViewById(R.id.ll_main_tab_home);
        // 中间Tab：产品
        mLlMainTabProduct = (LinearLayout) this.findViewById(R.id.ll_main_tab_product);
        // 我的Tab：我的
        mLlMainTabMy = (LinearLayout) this.findViewById(R.id.ll_main_tab_my);
        // Tab布局
        mLlMainTab = (LinearLayout) this.findViewById(R.id.ll_main_tab);
    }

    public void fillView() {

        switchFragment(FRAGMENT_TAB_FLAG_HOME);
    }

    public void initListener() {
        mLlMainTabHome.setOnClickListener(this);
        mLlMainTabProduct.setOnClickListener(this);
        mLlMainTabMy.setOnClickListener(this);
    }

    /**
     * 切换Fragment
     *
     * @param index
     */
    public void switchFragment(int index) {
        mCurrentTabFlagIndex = index;

        currentFragment = (BaseViewFragment) mFragmentsList.get(index);

        if (!isAddFrames(currentFragment)) {
            this.getSupportFragmentManager().beginTransaction()
                    .add(R.id.fl_activity_main_viewpager, mFragmentsList.get(mCurrentTabFlagIndex)).commit();
            mCurFragmentsList.add(currentFragment);
        }
        for (int i = 0; i < mCurFragmentsList.size(); i++) {
            this.getSupportFragmentManager().beginTransaction().hide(mCurFragmentsList.get(i)).commit();
        }

        this.getSupportFragmentManager().beginTransaction().show(currentFragment).commit();
        try {
            changeTabColor(index);
        } catch (Exception e) {

        }
    }

    /**
     * 切换时变色
     *
     * @param index
     */
    private void changeTabColor(int index) {
        for (int i = 0; i < mLlMainTab.getChildCount(); i++) {

            LinearLayout currentview = (LinearLayout) mLlMainTab.getChildAt(i);
            ImageView currentImage = (ImageView) currentview.getChildAt(0);
            TextView current_TextView = (TextView) currentview.getChildAt(1);
            switch (i) {
                case 0:
                    if (index == i) {
                        currentImage.setImageResource(R.mipmap.ic_tab_home_check);
                    } else {
                        currentImage.setImageResource(R.mipmap.ic_tab_home_default);
                    }
                    break;

                case 1:
                    if (index == i) {
                        currentImage.setImageResource(R.mipmap.ic_tab_products_check);
                    } else {
                        currentImage.setImageResource(R.mipmap.ic_tab_products_default);
                    }
                    break;

                case 2:
                    if (index == i) {
                        currentImage.setImageResource(R.mipmap.ic_tab_my_check);
                    } else {
                        currentImage.setImageResource(R.mipmap.ic_tab_my_default);
                    }
                    break;
            }

            if (i == index) {
                current_TextView.setTextColor(this.getResources().getColor(R.color.main_tab_text_color_select));
            } else {
                current_TextView.setTextColor(this.getResources().getColor(R.color.main_tab_text_color_unselect));
            }
        }
    }

    public boolean isAddFrames(Fragment current) {
        boolean booladd = false;
        for (int i = 0; i < mCurFragmentsList.size(); i++) {
            if (mCurFragmentsList.get(i) == current) {
                booladd = true;
                break;
            }
        }
        return booladd;
    }

    /**
     * 点击事件。
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_main_tab_home:
                switchFragment(FRAGMENT_TAB_FLAG_HOME);

                break;
            case R.id.ll_main_tab_product:


                switchFragment(FRAGMENT_TAB_FLAG_PRODUCT);
                break;

            case R.id.ll_main_tab_my:
//                switchFragment(FRAGMENT_TAB_FLAG_MY);
                startActivity(new Intent(this, LoginRegisterActivity.class));
                break;

            default:
                break;
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
