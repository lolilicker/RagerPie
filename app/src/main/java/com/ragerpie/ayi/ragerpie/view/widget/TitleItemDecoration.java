package com.ragerpie.ayi.ragerpie.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.model.beans.OrderBean;
import com.ragerpie.ayi.ragerpie.util.DpAndPx;
import com.ragerpie.ayi.ragerpie.util.LogUtils;

import java.util.List;

/**
 * Created by WangBo on 2016/10/31.
 */

public class TitleItemDecoration extends RecyclerView.ItemDecoration {
    private static final int TITLE_HEIGHT = DpAndPx.convertDpToPixel(20);
    private static final int TEXT_SIZE = DpAndPx.sp2px(12);
    private static final int LEFT_PADDING = DpAndPx.convertDpToPixel(10);
    private static int COLOR_TITLE_FONT;
    private static int COLOR_TITLE_BG;
    private Paint paint;

    private List<OrderBean> dataList;
    private Rect textBound;

    public TitleItemDecoration(List<OrderBean> dataList, Context context) {
        this.dataList = dataList;
        COLOR_TITLE_FONT = context.getResources().getColor(R.color.titleDecorationFont);
        COLOR_TITLE_BG = context.getResources().getColor(R.color.titleDecorationBG);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(TEXT_SIZE);
        textBound = new Rect();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            final int topMargin = ((RecyclerView.LayoutParams) child.getLayoutParams()).topMargin;
            final int position = ((RecyclerView.LayoutParams) child.getLayoutParams()).getViewLayoutPosition();
            OrderBean orderBean = dataList.get(position);
            if (position > -1) {
                if (position == 0
                        || !orderBean.getDateStr().equals(dataList.get(position - 1).getDateStr())) {
                    LogUtils.d("onDraw " + position);
                    paint.setColor(COLOR_TITLE_BG);
                    c.drawRect(left,
                            child.getTop() - topMargin - TITLE_HEIGHT,
                            right,
                            child.getTop() - topMargin,
                            paint);
                    paint.setColor(COLOR_TITLE_FONT);
                    paint.getTextBounds(orderBean.getDateStr(), 0, orderBean.getDateStr().length(), textBound);
                    c.drawText(orderBean.getDateStr(),
                            child.getPaddingLeft() + LEFT_PADDING,
                            child.getTop() - topMargin - textBound.height() / 2,
                            paint);
                }
            }
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        if (position > -1){
            OrderBean orderBean = dataList.get(position);
            paint.setColor(COLOR_TITLE_BG);
            c.drawRect(parent.getPaddingLeft(), parent.getPaddingTop(), parent.getWidth() - parent.getPaddingRight(),
                    parent.getPaddingTop() + TITLE_HEIGHT, paint);
            paint.setColor(COLOR_TITLE_FONT);
            paint.getTextBounds(orderBean.getDateStr(), 0, orderBean.getDateStr().length(), textBound);
            c.drawText(orderBean.getDateStr(), parent.getPaddingLeft()+LEFT_PADDING,
                    parent.getTop() + parent.getPaddingTop() + TITLE_HEIGHT - textBound.height() / 2, paint);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        final int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        OrderBean orderBean = dataList.get(position);
        if (position > -1) {
            if (position == 0 || !orderBean.getDateStr().equals(dataList.get(position - 1).getDateStr())) {
                outRect.set(0, TITLE_HEIGHT, 0, 0);
            } else {
                outRect.set(0, 0, 0, 0);
            }
        }
    }
}
