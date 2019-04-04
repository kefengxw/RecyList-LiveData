package com.RecyList.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.TypedValue;
import android.view.View;

import com.RecyList.android.R;
import com.RecyList.android.util.UtilString;

import java.util.ArrayList;

public class TitleDecoration extends ItemDecoration {

    private ArrayList<ItemRecyclerDisplayData> mData = null;
    private Paint mPaint = null;
    private Rect mBounds = null;
    private int mTitleHeight = 0;
    private int mBgColor = 0;
    private int mTextColor = 0;
    private int mTitleFontSize = 0;

    public TitleDecoration(Context ctx) {

        Resources res = ctx.getResources();
        mPaint = new Paint();
        mBounds = new Rect();
        mBgColor = ContextCompat.getColor(ctx, R.color.colorRecyclerTitleBg);
        mTextColor = ContextCompat.getColor(ctx, R.color.colorRecyclerTitleText);
        mTitleHeight = res.getDimensionPixelSize(R.dimen.item_recycler_title_height);
        mTitleFontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                res.getDimensionPixelSize(R.dimen.item_recycler_title_text_size), res.getDisplayMetrics());
        mPaint.setTextSize(mTitleFontSize);
        mPaint.setAntiAlias(true);
    }

    public void setData(ArrayList<ItemRecyclerDisplayData> data) {
        this.mData = data;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View item, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, item, parent, state);
        //the position is index of mData, this function is for itemViews to draw
        int position = ((RecyclerView.LayoutParams) item.getLayoutParams()).getViewLayoutPosition();
        if (position != RecyclerView.NO_POSITION) {
            if ((position == 0) || (!inSameGroup(position))) {
                outRect.set(0, mTitleHeight, 0, 0);//outRect.set(0,0,0,0) is default
            }
        }
    }

    private boolean inSameGroup(int position) {

        if (mData == null || mData.size() == 0 || position == 0) {
            return false;
        }

        String pre = UtilString.getIndex(mData.get(position - 1).getName());
        String cur = UtilString.getIndex(mData.get(position).getName());

        return pre.equals(cur);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        //this function is for ItemDecoration to draw
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        View itemView = null;

        for (int i = 0; i < childCount; i++) {
            itemView = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) itemView.getLayoutParams();
            int position = params.getViewLayoutPosition();

            if (position != RecyclerView.NO_POSITION) {
                if ((position == 0) || (!inSameGroup(position))) { //first one
                    onDrawTitle(c, left, right, itemView, params, position);
                }
            }
        }
    }

    private void onDrawTitle(Canvas c, int left, int right, View itemView, RecyclerView.LayoutParams params, int position) {
        mPaint.setColor(mBgColor);
        c.drawRect(left, itemView.getTop() - params.topMargin - mTitleHeight, right, itemView.getTop() - params.topMargin, mPaint);

        //draw text on Background
        mPaint.setColor(mTextColor);

        String curFirstLetter = UtilString.getIndex(mData.get(position).getName());
        mPaint.getTextBounds(curFirstLetter, 0, curFirstLetter.length(), mBounds);
        c.drawText(curFirstLetter, itemView.getPaddingLeft(), itemView.getTop() - params.topMargin - (mTitleHeight / 2 - mBounds.height() / 2), mPaint);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
