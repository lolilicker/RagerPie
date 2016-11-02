package com.ragerpie.ayi.ragerpie.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ragerpie.ayi.ragerpie.view.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by WangBo on 2016/10/28.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initVariable();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    protected abstract void initVariable();

    protected abstract void initView(View view);

    protected abstract void loadData();

    protected abstract int getLayoutResId();

    public abstract String getTitle();

    protected void showToast(String message) {
        BaseActivity activity = (BaseActivity) getActivity();
        activity.showToast(message);
    }

}
