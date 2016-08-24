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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_action;
    private Switch switch_alpha;
    private Switch switch_trans;
    private SeekBar sb_duration;
    private LinearLayout ll;

    int duration = 300;
    private TextView tv_duration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_action = (Button) findViewById(R.id.btn_action);
        switch_alpha = (Switch) findViewById(R.id.switch_alpha);
        switch_trans = (Switch) findViewById(R.id.switch_trans);
        sb_duration = (SeekBar) findViewById(R.id.sb_duration);
        ll = (LinearLayout) findViewById(R.id.linearLayout);
        tv_duration = (TextView) findViewById(R.id.tv_duration);

        initLis();
    }
    private void initLis(){
        btn_action.setOnClickListener(this);
        sb_duration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                duration = progress + 300;
                tv_duration.setText("时间："+ (progress + 300) + "毫秒");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void anim(View view,boolean isOpen){
        ObjectAnimator animAlpha = null;
        ObjectAnimator animTrans = null;
        if (isOpen){
            if (isAlphaOpen())
            animAlpha = ObjectAnimator.ofFloat(view,"alpha" ,1f,0f);
            if (isTransOpen())
            animTrans = ObjectAnimator.ofFloat(view,"TranslationX",0,view.getWidth());
        }else {
            if (isAlphaOpen())
            animAlpha = ObjectAnimator.ofFloat(view,"alpha" ,0f,1f);
            if (isTransOpen())
            animTrans = ObjectAnimator.ofFloat(view,"TranslationX",view.getWidth(),0);
        }

        AnimatorSet animSet = new AnimatorSet();
        if(animAlpha != null)
            animSet.play(animAlpha);
        if (animTrans != null)
            animSet.play(animTrans);
//        animSet.playTogether(animAlpha,animTrans);

//        animTrans.start();
//        animAlpha.start();
//        view.startAnimation();

        animSet.setDuration(duration);

        if(animSet.getChildAnimations().size() > 0){
            animSet.start();
        }else{
            Toast.makeText(this,"还没有选择任何动画效果",Toast.LENGTH_SHORT).show();
        }
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
