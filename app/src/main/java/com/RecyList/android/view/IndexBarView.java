package com.RecyList.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.RecyList.android.R;

import java.util.Arrays;
import java.util.List;

import static com.RecyList.android.model.data.InternalDataConfiguration.INDEX_BAR_LETTER_SPLIT;

public class IndexBarView extends View {

    private OnTouchEventListener mListener = null;
    private Resources mResources = null;
    private List<String> mLetters = null;
    private TextView mHintTextView = null;
    private Paint mLetterPaint = new Paint();//Text Paint
    private float mLetterSize = 0;
    private int mLetterColor = 0;
    private int mChoosePosition = -1;
    private int mOldChoosePosition = -1;
    private int mHeight = 0;
    private int mWidth = 0;
    private int mLetterHeight = 0;

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
        mLetterColor = ContextCompat.getColor(ctx, R.color.colorIndexBarText);
    }

    private void updateViewParam() {
        mHeight = getHeight();
        mWidth = getWidth();
        mLetterHeight = mHeight / mLetters.size();//maybe add some padding here
        mLetterSize = mLetterHeight * INDEX_BAR_LETTER_SPLIT;//Golden Section 0.618
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        updateViewParam();
        drawLetter(canvas);
    }

    private void drawLetter(Canvas canvas) {

        float x = 0;
        float y = 0;//for each letter
        String cur = null;

        setLetterPaint(mLetterPaint);

        for (int i = 0; i < mLetters.size(); i++) {
            cur = mLetters.get(i);
            x = (mWidth - mLetterPaint.measureText(cur)) / 2;
            y = mLetterHeight * (i + 1);
            canvas.drawText(cur, x, y, mLetterPaint);
        }
    }

    private void setLetterPaint(Paint letterPaint) {
        letterPaint.setColor(mLetterColor);
        letterPaint.setTextSize(mLetterSize);
        //mLetterPaint.setTypeface();//Letter Type
        //letterPaint.setFakeBoldText(false);
        letterPaint.setAntiAlias(true);
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
            String cur = mLetters.get(mChoosePosition);
            mHintTextView.setText(cur);
            if (mListener != null) {
                mListener.onTouchListener(cur);
            }
        }
        mOldChoosePosition = mChoosePosition;
    }

    public interface OnTouchEventListener {
        void onTouchListener(String it);
    }

    public void setOnTouchEventListener(OnTouchEventListener it) {
        mListener = it;
    }
}