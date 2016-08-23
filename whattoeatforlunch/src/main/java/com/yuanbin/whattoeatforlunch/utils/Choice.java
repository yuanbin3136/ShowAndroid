package com.yuanbin.whattoeatforlunch.utils;

import java.util.Random;

/**
 * Created by John on 2016/8/24.
 */
public class Choice {
    private Thread mThread;
    private String[] items = new String[]{"黄焖鸡","排骨饭","汉堡","鸡腿饭","拉面","不吃了"};
    boolean isRun;
    int i = 0;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true){
                if (!isRun)
                    break;
                int i = new Random().nextInt(items.length);
                onChoice.onChoice(items[i]);
                try {
                    if (!isRun)
                        break;
                    mThread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    public void stop(){
        isRun = false;
        mThread.interrupt();
    }
    public void start(){
        if (!isRun)
            init();
        isRun = true;
        mThread.start();
    }

    public void init(){
        mThread = new Thread(runnable);
    }
    public Choice(OnChoice onChoice) throws Exception{
        if (onChoice == null)
            throw new Exception("不能为空啊");
        init();
        this.onChoice = onChoice;
    }
    public interface OnChoice{
        void onChoice(String item);
    }
    OnChoice onChoice;
}
