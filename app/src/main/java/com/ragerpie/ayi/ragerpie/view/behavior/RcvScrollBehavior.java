package com.ragerpie.ayi.ragerpie.view.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.ragerpie.ayi.ragerpie.util.LogUtils;

/**
 * Created by WangBo on 2016/11/9.
 */

public class RcvScrollBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {
    public RcvScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return true;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        if (dependency instanceof RelativeLayout) {
            RecyclerView recyclerView = (RecyclerView) dependency.findViewWithTag("rcv_data_list");
            if (!recyclerView.canScrollVertically(-1)) {
                child.setVisibility(View.GONE);
            } else {
                child.setVisibility(View.VISIBLE);
            }
            LogUtils.d("recyclerView" + recyclerView.computeVerticalScrollOffset());
        }
        return super.onDependentViewChanged(parent, child, dependency);
    }
}
