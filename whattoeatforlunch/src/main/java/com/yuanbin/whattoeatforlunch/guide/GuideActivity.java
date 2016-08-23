package com.yuanbin.whattoeatforlunch.guide;

import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

import com.yuanbin.whattoeatforlunch.base.BaseActivity;
import com.yuanbin.whattoeatforlunch.MainActivity;
import com.yuanbin.whattoeatforlunch.R;

/**
 * Created by John on 2016/8/23.
 */
public class GuideActivity extends BaseActivity {
    @Override
    public int getResourcesId() {
        return R.layout.layout_guide;
    }

    @Override
    protected void initViewBefore() {
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 去除标题  必须在setContentView()方法之前调用
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置全屏
        super.initViewBefore();
    }

    @Override
    protected void initViewAfter() {
        weakHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toMain();
            }
        },1 * 1000);
        super.initViewAfter();
    }

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        requestWindowFeature(Window.FEATURE_NO_TITLE); // 去除标题  必须在setContentView()方法之前调用
//        setContentView(R.layout.layout_guide);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置全屏
//        super.onCreate(savedInstanceState);
//
//        toMain();
//
//    }

    private void toMain(){
        Intent intent = new Intent(this,MainActivity.class);
        //第一个参数为启动时动画效果，第二个参数为退出时动画效果
        overridePendingTransition(R.anim.activity_fade, R.anim.acitivity_hold);
        startActivity(intent);
        finish();
    }
}
