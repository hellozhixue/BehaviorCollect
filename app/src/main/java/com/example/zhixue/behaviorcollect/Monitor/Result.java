package com.example.zhixue.behaviorcollect.Monitor;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.zhixue.behaviorcollect.example.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.zhixue.behaviorcollect.Monitor.Monitor.sLastPageName;
import static com.example.zhixue.behaviorcollect.Monitor.Monitor.sShowLog;
import static com.example.zhixue.behaviorcollect.Monitor.Monitor.sStart;

/**
 * Created by dell on 2018/1/8.
 */

public class Result {

     /*
    *打开app时上传的数据
     */
    public static void onAppStart(Activity activity) {
        if (!sStart.compareAndSet(false, true)) {
            return;
        }
        if (sShowLog) Log.d("collectMessage" , "app被打开了 ");
        //TODO   添加你的代码

    }

    /*
    *打开页面上传的数据
    * object  是activity或者Fragment
     */
    public static  void onPageStart(Object object) {
        if (sShowLog) Log.d("collectMessage" , "打开了新页面: "+object.getClass().getSimpleName()+"，上一页面="+sLastPageName);
        //TODO   添加你的代码

    }
    /*
        *点击按钮时上传的数据
         */
    public static void onClickButton(String idName, String viewName, String viewType) {
        if (sShowLog) Log.d("collectMessage" , viewName+" 按钮被点击 idName = "+idName+"，viewType = "+viewType);
        //TODO   添加你的代码


    }

}
