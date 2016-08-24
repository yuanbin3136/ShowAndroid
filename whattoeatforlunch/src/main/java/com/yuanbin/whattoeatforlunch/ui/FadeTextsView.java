package com.yuanbin.whattoeatforlunch.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.yuanbin.whattoeatforlunch.utils.L;
import com.yuanbin.whattoeatforlunch.utils.UtilsSrceen;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by John on 2016/8/24.
 */
public class FadeTextsView extends View{
    public FadeTextsView(Context context) {
        this(context,null);
    }

    public FadeTextsView(Context context, AttributeSet attrs) {
        this(context,null,0);
    }

    public FadeTextsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private String[] texts;
    HashMap<Integer,Integer> locations = new HashMap<>();
    private void init(){
        texts = new String[]{"黄焖鸡","排骨饭","汉堡","鸡腿饭","拉面","不吃了"};

        int h = UtilsSrceen.getScreenH(getContext()) - 4 * 100 ;
        int w = UtilsSrceen.getScreenW(getContext()) - 4 * 100;
        for (int i = 0 ;i < texts.length ; i++){
            locations.put(new Random().nextInt(w),new Random().nextInt(h));
        }
        L.e(this,"locations.size():" + locations.size());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        L.e(this,"onLayout" + "changed" + changed + " left:" + left + " top:"+ top + " right:"+ right+ " bottom:" + bottom);
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        L.e(this,"onMeasure");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    boolean isOnDraw = false;
    @Override
    protected void onDraw(Canvas canvas) {
        L.e(this,"onDraw");
//        canvas.drawColor(Color.GRAY);
        if (!isOnDraw)
        for (int i = 0;i < texts.length;i++){
            drawText(canvas,texts[i] ,i);
        }
        super.onDraw(canvas);
//        isOnDraw = true;
    }
    private void drawText(Canvas canvas,String text,int i){
        canvas.save();
        /*
        开始写字
         */
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLACK);

        mPaint.setTextSize(70);
        //new Random().nextInt(500)
        Set<Map.Entry<Integer, Integer>> set = locations.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = set.iterator();
        Map.Entry<Integer, Integer> entry = null;
        while (i > -1 ){
            i--;
            entry = iterator.next();
        }
        canvas.drawText(text,entry.getKey(), entry.getValue(),mPaint);
        L.e(this,text + entry.getKey());
        canvas.restore();
    }
}
