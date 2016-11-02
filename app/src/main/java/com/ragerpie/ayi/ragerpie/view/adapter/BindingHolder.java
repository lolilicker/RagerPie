package com.ragerpie.ayi.ragerpie.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ragerpie.ayi.ragerpie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WangBo on 2016/10/28.
 */

public class BindingHolder extends RecyclerView.ViewHolder {
    @Nullable
    @BindView(R.id.rcv_goods)
    public RecyclerView recyclerView;

    private ViewDataBinding binding;

    public BindingHolder(View rowView) {
        super(rowView);
        binding = DataBindingUtil.bind(rowView);
        ButterKnife.bind(this, rowView);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
