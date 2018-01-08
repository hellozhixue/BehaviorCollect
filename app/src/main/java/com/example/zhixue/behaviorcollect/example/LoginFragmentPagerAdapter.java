package com.example.zhixue.behaviorcollect.example;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.zhixue.behaviorcollect.example.fragment.BaseViewFragment;
import com.example.zhixue.behaviorcollect.example.fragment.RegisterFragment;
import com.example.zhixue.behaviorcollect.example.fragment.LoginFragment;

/**
 * 登录注册
 */

public class LoginFragmentPagerAdapter extends FragmentPagerAdapter {
    public final int COUNT = 2;
    private String[] titles = new String[]{"登录", "注册"};
    private Context context;
    private BaseViewFragment fragment;

    public LoginFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                fragment = new LoginFragment();
                break;
            case 1:
                fragment = new RegisterFragment();
                break;
            default:
                fragment =  new LoginFragment();;
                break;

        }
        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
