package com.yuanbin.whattoeatforlunch.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.yuanbin.whattoeatforlunch.utils.L;
import com.yuanbin.whattoeatforlunch.utils.WeakHandler;

/**
 * Created by John on 2016/8/23.
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected WeakHandler weakHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initHander();
        initViewBefore();
        initView();
        initViewAfter();
        super.onCreate(savedInstanceState);
    }

    private void initHander(){
        weakHandler = new WeakHandler();
        L.out("???");
    }
    protected void initViewBefore(){
        L.out("xxxxx");
    }
    protected void initViewAfter(){

    }

    private void initView(){
        setContentView(getResourcesId());
    }
    public abstract int getResourcesId();
}
