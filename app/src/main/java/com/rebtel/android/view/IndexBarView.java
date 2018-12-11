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
import android.widget.TextView;

import com.rebtel.android.R;

import java.util.Arrays;
import java.util.List;

public class IndexBarView extends View {

    private Resources mResources = null;
    private List<String> mLetters = null;
    private TextView mHintTextView = null;
    private int mLetterSize = 0;
    //private int mHintTextSize = 0;
    private int mLetterColor = 0;
    //private int mHintTextColor = 0;
    private int mChooseTextColor = 0;
    //private int mPressBgColor = 0;
    private int mChoosePosition = -1;
    private int mOldChoosePosition = -1;

    int mHeight = 0;
    int mWidth = 0;
    int mLetterHeight = 0;

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

    public void setHintText(TextView it) {
        mHintTextView = it;
    }

    private void init(Context ctx, AttributeSet attrs) {
        mResources = ctx.getResources();
        mLetters = Arrays.asList(mResources.getStringArray(R.array.indexBarLetter));

        mLetterSize = mResources.getDimensionPixelSize(R.dimen.index_bar_text_size);
        //mHintTextSize = mResources.getDimensionPixelSize(R.dimen.index_bar_hint_text_size);

        mLetterColor = ContextCompat.getColor(ctx, R.color.colorIndexBarText);
        //mHintTextColor = ContextCompat.getColor(ctx, R.color.colorIndexBarHintText);
        mChooseTextColor = ContextCompat.getColor(ctx, R.color.colorIndexBarChooseText);
        //mPressBgColor = ContextCompat.getColor(ctx, R.color.colorIndexBarPressBg);

        if (attrs != null) {
            TypedArray it = ctx.obtainStyledAttributes(attrs, R.styleable.IndexBarView);
            //it.getColor(R.styleable.IndexBarView_index_bar_text_color, )
            it.recycle();
        }

        //mTextPaint.setAntiAlias(true);
        //mTextPaint.setColor();

    }

    private void updateViewParam() {
        mHeight = getHeight();
        mWidth = getWidth();
        mLetterHeight = mHeight / mLetters.size();//maybe add some padding here
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        updateViewParam();
        //1. draw Letter
        drawLetter(canvas);
        drawChooseText(canvas);
    }

    private void drawLetter(Canvas canvas) {
        float x = 0;
        float y = 0;//for each letter
        String cur = null;

        setLetterPaint(mLetterPaint);

        for (int i = 0; i < mLetters.size(); i++) {
            if (i == mChoosePosition) {
                setLetterPaintForChoosePosition(mLetterPaint);
            }

            cur = mLetters.get(i);
            x = (mWidth - mLetterPaint.measureText(cur)) / 2;
            y = mLetterHeight * (i + 1);
            canvas.drawText(cur, x, y, mLetterPaint);

            if (i == mChoosePosition) {
                setLetterPaint(mLetterPaint);
            }//recover the paint
        }
    }

    private void setLetterPaint(Paint letterPaint) {
        letterPaint.setColor(mLetterColor);
        letterPaint.setTextSize(mLetterSize);
        //mLetterPaint.setTypeface();//Letter Type
        letterPaint.setAntiAlias(true);
        letterPaint.setFakeBoldText(false);
    }

    private void setLetterPaintForChoosePosition(Paint letterPaint) {
        letterPaint.setColor(mChooseTextColor);
        letterPaint.setFakeBoldText(true);
    }


    private void drawChooseText(Canvas canvas) {

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        super.dispatchTouchEvent(event);
        float x = event.getX();//each pointer of the screen
        float y = event.getY();
        int action = event.getAction();

        if (mHintTextView == null) {
            return false;
        }

        mOldChoosePosition = mChoosePosition;
        mChoosePosition = (int) y / mLetterHeight;

        switch (action) {
            case MotionEvent.ACTION_UP://leave
                handleTouchEventActionUp();
                break;
            default://others, DOWN and MOVE
                handleTouchEventActionMove();
                break;
        }
        return true;
    }

    private void handleTouchEventActionUp() {
        mHintTextView.setVisibility(INVISIBLE);
        mChoosePosition = -1;
    }

    private void handleTouchEventActionMove() {
        mHintTextView.setVisibility(VISIBLE);
        if ((mChoosePosition != mOldChoosePosition) && (mChoosePosition >= 0) && (mChoosePosition < mLetters.size())) {
            mHintTextView.setText(mLetters.get(mChoosePosition));
        }
        mOldChoosePosition = mChoosePosition;
    }
}
