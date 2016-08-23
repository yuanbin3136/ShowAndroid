package com.yuanbin.whattoeatforlunch.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by John on 2016/8/23.
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    /**
     * 获取的app的Application对象
     *
     * @param context 上下文
     * @return Application对象
     */
    public static BaseApplication from(Context context) {
        return (BaseApplication) context.getApplicationContext();
    }
}
