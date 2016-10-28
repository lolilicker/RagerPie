package com.ragerpie.ayi.ragerpie.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.view.adapter.OrderFragmentPagerAdapter;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tl_main)
    TabLayout tlMainActivity;
    @BindView(R.id.vp_main)
    ViewPager vpMainActivity;

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initView() {
        toolbar.setTitle("xxx");
        tlMainActivity.addTab(tlMainActivity.newTab());
        tlMainActivity.addTab(tlMainActivity.newTab());
        tlMainActivity.addTab(tlMainActivity.newTab());
        vpMainActivity.setAdapter(new OrderFragmentPagerAdapter(getSupportFragmentManager()));
        vpMainActivity.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMainActivity));
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

}
