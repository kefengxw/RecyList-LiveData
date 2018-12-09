package com.rebtel.android.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

import com.rebtel.android.R;

public class TitleDecoration extends ItemDecoration {

    private int splitLineHeight = 0;
    private Paint mPaint = null;

    public TitleDecoration(Context ctx) {

        Resources res = ctx.getResources();

        mPaint = new Paint();
        mPaint.setColor(ContextCompat.getColor(ctx, R.color.colorSplitLine));
        splitLineHeight = res.getDimensionPixelSize(R.dimen.item_split_line_height);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View item, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, item, parent, state);
        //the position is index of mData
        int position = ((RecyclerView.LayoutParams) item.getLayoutParams()).getViewLayoutPosition();
        if (RecyclerView.NO_POSITION != position) {
            if (0 != position) {
                outRect.top = splitLineHeight;
            }
        }
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int childCount = parent.getChildCount();
        int x = 0;
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
