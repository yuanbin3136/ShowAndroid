package com.yuanbin.androidshow;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_action;
    private Switch switch_alpha;
    private Switch switch_trans;
    private SeekBar sb_duration;
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_action = (Button) findViewById(R.id.btn_action);
        switch_alpha = (Switch) findViewById(R.id.switch_alpha);
        switch_trans = (Switch) findViewById(R.id.switch_trans);
        sb_duration = (SeekBar) findViewById(R.id.sb_duration);
        ll = (LinearLayout) findViewById(R.id.linearLayout);
        initLis();
    }
    private void initLis(){
        btn_action.setOnClickListener(this);
    }
    private void anim(View view,boolean isOpen){
        ObjectAnimator animAlpha;
        ObjectAnimator animTrans;
        if (isOpen){
            animAlpha = ObjectAnimator.ofFloat(view,"alpha" ,1f,0f);
            animTrans = ObjectAnimator.ofFloat(view,"TranslationX",0,view.getWidth());
        }else {
            animAlpha = ObjectAnimator.ofFloat(view,"alpha" ,0f,1f);
            animTrans = ObjectAnimator.ofFloat(view,"TranslationX",view.getWidth(),0);
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.playTogether(animAlpha,animTrans);
//        animTrans.start();
//        animAlpha.start();
//        view.startAnimation();
        animSet.start();
    }

    boolean isOpen;
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_action:
                isOpen = !isOpen;
                anim(ll,!isOpen);
                break;
            default:
        }
    }
    private boolean isAlphaOpen(){
        return switch_alpha.isChecked();
    }
    private boolean isTransOpen(){
        return switch_trans.isChecked();
    }
}
