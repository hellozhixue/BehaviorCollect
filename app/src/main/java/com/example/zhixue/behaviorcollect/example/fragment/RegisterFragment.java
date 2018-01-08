package com.example.zhixue.behaviorcollect.example.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhixue.behaviorcollect.R;
import com.example.zhixue.behaviorcollect.example.fragment.BaseViewFragment;

// * 注册


@SuppressLint("ResourceAsColor")
public class RegisterFragment extends BaseViewFragment implements OnClickListener {

    private TextView register_btn_getSMS;// 点击发送短信的按钮
    private TextView register_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
        //初始化布局
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        register_btn = (TextView) getView().findViewById(R.id.register_btn);// 注册按钮
        register_btn_getSMS = (TextView) getView().findViewById(R.id.register_btn_getSMS);// 获取短信验证码

        register_btn.setOnClickListener(this);
        register_btn_getSMS.setOnClickListener(this);// 获取短信验证码

    }

//*
//     * 点击事件
    @Override
    public void onClick(View arg0) {
        int id = arg0.getId();
        switch (id) {

// 发送短信验证码
            case R.id.register_btn_getSMS:

                break;
            case R.id.register_btn:
                break;
            default:
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
