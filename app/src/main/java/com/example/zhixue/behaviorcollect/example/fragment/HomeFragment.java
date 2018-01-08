package com.example.zhixue.behaviorcollect.example.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhixue.behaviorcollect.R;

/**
 * 首页。
 */
public class HomeFragment extends BaseViewFragment implements View.OnClickListener {

    private TextView mTvLogin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
        //初始化布局
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mTvLogin = (TextView) getView().findViewById(R.id.login_btn);
        mTvLogin.setEnabled(false);//登录的按钮默认置灰。
        mTvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            // 登录
            case R.id.login_btn:

                break;

        }
    }


}
