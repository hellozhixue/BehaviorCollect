package com.example.zhixue.behaviorcollect.example;

import android.app.Application;
import android.os.Build;

import com.example.zhixue.behaviorcollect.Monitor.Monitor;
import com.example.zhixue.behaviorcollect.Monitor.MonitorActivityLifecycleCallbacks;

/**
 * Created by dell3010 on 2017/12/26.
 */

public class MyApplication extends Application {
    private static MyApplication mInstance;//Application单例

    /**
     * 获得Application单例对象
     */
    public static MyApplication getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT > 14) {  //埋点统计回调监听
            Monitor.init(this,true);
        }

         /*其他内容初始化*/
        mInstance = this;
    }
}
