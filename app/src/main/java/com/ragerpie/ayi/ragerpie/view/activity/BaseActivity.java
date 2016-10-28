package com.ragerpie.ayi.ragerpie.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by WangBo on 2016/10/28.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
        setContentView(getLayoutResId());
        ButterKnife.bind(this);
        initView();
        loadData();
    }

    protected abstract void initVariable();

    protected abstract void initView();

    protected abstract void loadData();

    protected abstract int getLayoutResId();
}
