package com.example.zhixue.behaviorcollect.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhixue.behaviorcollect.R;
import com.example.zhixue.behaviorcollect.example.fragment.BaseViewFragment;


/**
 * 登录*/


public class LoginFragment extends BaseViewFragment implements View.OnClickListener {

    private TextView mTvLogin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         return inflater.inflate(R.layout.fragment_login, container, false);
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



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
