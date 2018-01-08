package com.example.zhixue.behaviorcollect.example;

import android.app.Application;
import android.os.Build;

import com.example.zhixue.behaviorcollect.Monitor.MonitorActivityLifecycleCallbacks;

/**
 * Created by dell3010 on 2017/12/26.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT > 14) {  //埋点统计回调监听
            this.registerActivityLifecycleCallbacks(new MonitorActivityLifecycleCallbacks());
        }
    }
}
