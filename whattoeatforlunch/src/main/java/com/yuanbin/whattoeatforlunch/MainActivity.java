package com.yuanbin.whattoeatforlunch;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.yuanbin.whattoeatforlunch.base.BaseActivity;
import com.yuanbin.whattoeatforlunch.utils.Choice;
import com.yuanbin.whattoeatforlunch.utils.L;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btn_start;
    private TextView tv_result;

    @Override
    public int getResourcesId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewAfter() {
        btn_start = (Button) findViewById(R.id.btn_start);
        tv_result = (TextView) findViewById(R.id.tv_result);
        initLis();
        super.initViewAfter();
    }
    private void initLis(){
        btn_start.setOnClickListener(this);
    }

    final int FLAG_CHOICE_PRE = 0;//没开始
    final int FLAG_CHOICE_START = 1;//开始了
    final int FLAG_CHOICE_STOP = 2;//停止了
    int choiceFlag = FLAG_CHOICE_PRE;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                switch (choiceFlag){
                    case FLAG_CHOICE_PRE:
                        //让它开始；
                        choiceFlag = FLAG_CHOICE_START;
                        try {
                            choice = new Choice(onChoice);
                            choice.start();
                        } catch (Exception e) {
                            e.printStackTrace();
                            choiceFlag = FLAG_CHOICE_PRE;
                        }
                        L.e(this,"点击开始了");
                        break;
                    case FLAG_CHOICE_START:
                        //让他停
                        choiceFlag = FLAG_CHOICE_STOP;
                        choice.stop();
                        L.e(this,"点击停止");
                        break;
                    case FLAG_CHOICE_STOP:
                        choiceFlag = FLAG_CHOICE_START;
                        //让他重新开始
                        choice.start();
                        L.e(this,"点击重新开始");
                        break;
                }showChoice();
                break;
            default:
        }
    }
    Choice choice;
    Choice.OnChoice onChoice = new Choice.OnChoice() {
        @Override
        public void onChoice(final String item) {
            L.i("选好了", item);
            weakHandler.post(new Runnable() {
                @Override
                public void run() {
                    tv_result.setText(item);
                }
            });
        }
    };
    private void showChoice(){
        switch (choiceFlag){
            case FLAG_CHOICE_PRE:
                //到不了这里；
                btn_start.setText("今天中午吃什么");
                L.i(this,"今天中午吃什么");
                break;
            case FLAG_CHOICE_START:
                //显示停止了
                 btn_start.setText("停止");
                break;
            case FLAG_CHOICE_STOP:
                //显示重新开始
                btn_start.setText("重新开始");
                break;
        }
    }
}
