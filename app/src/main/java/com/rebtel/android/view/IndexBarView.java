package com.rebtel.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.rebtel.android.R;

import java.util.Arrays;
import java.util.List;

public class IndexBarView extends View {

    private Resources mResources = null;
    private List<String> mLetters;
    private int mLetterSize = 0;
    private int mHintTextSize = 0;
    private int mLetterColor = 0;
    private int mHintTextColor = 0;
    private int mChooseTextColor = 0;
    private int mPressBgColor = 0;
    private int mChoosePosition = -1;

    private Paint mLetterPaint = new Paint();//Text Paint
    private Paint mTextPaint = new Paint();//Text Paint

    public IndexBarView(Context context) {
        this(context, null, 0);
    }

    public IndexBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IndexBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context ctx, AttributeSet attrs) {
        mResources = ctx.getResources();
        mLetters = Arrays.asList(mResources.getStringArray(R.array.indexBarLetter));

        mLetterSize = mResources.getDimensionPixelSize(R.dimen.index_bar_text_size);
        mHintTextSize = mResources.getDimensionPixelSize(R.dimen.index_bar_hint_text_size);

        mLetterColor = ContextCompat.getColor(ctx, R.color.colorIndexBarText);
        mHintTextColor = ContextCompat.getColor(ctx, R.color.colorIndexBarHintText);
        mChooseTextColor = ContextCompat.getColor(ctx, R.color.colorIndexBarChooseText);
        mPressBgColor = ContextCompat.getColor(ctx, R.color.colorIndexBarPressBg);

        if (attrs != null) {
            TypedArray it = ctx.obtainStyledAttributes(attrs, R.styleable.IndexBarView);
            //it.getColor(R.styleable.IndexBarView_index_bar_text_color, )
            it.recycle();
        }

        //mTextPaint.setAntiAlias(true);
        //mTextPaint.setColor();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //1. draw Letter
        drawLetter(canvas);
        drawChooseText(canvas);
    }

    private void drawLetter(Canvas canvas){
        int height = getHeight();
        int width = getWidth();
        int letterHeight = height/mLetters.size();//maybe add some padding here
        float x = 0;
        float y = 0;
        String cur = null;

        setLetterPaint(mLetterPaint);

        for (int i=0; i<mLetters.size(); i++){

            cur = mLetters.get(i);

            if (i == mChoosePosition){
                setLetterPaintForChoosePosition(mLetterPaint);
            }

            x = (width - mLetterPaint.measureText(cur))/2;
            y = letterHeight*(i+1);

            canvas.drawText(cur, x, y, mLetterPaint);

            if (i == mChoosePosition){
                setLetterPaint(mLetterPaint);//recover the paint
            }
        }
    }

    private void setLetterPaint(Paint letterPaint){
        letterPaint.setColor(mLetterColor);
        letterPaint.setTextSize(mLetterSize);
        //mLetterPaint.setTypeface();//Letter Type
        letterPaint.setAntiAlias(true);
        letterPaint.setFakeBoldText(false);
    }

    private void setLetterPaintForChoosePosition(Paint letterPaint){
        letterPaint.setColor(mChooseTextColor);
        letterPaint.setFakeBoldText(true);
    }


    private void drawChooseText(Canvas canvas){

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);




        return true;
    }
}
