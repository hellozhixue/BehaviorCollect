package com.example.zhixue.behaviorcollect.example.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhixue.behaviorcollect.R;

/**
 *
 */

public class ServiceFragment extends BaseViewFragment  implements View.OnClickListener{

    private TextView mTvLogin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_services, container, false);
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
