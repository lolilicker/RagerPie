package com.ragerpie.ayi.ragerpie.view.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.event.FloatActionBarEvent;
import com.ragerpie.ayi.ragerpie.view.adapter.OrderFragmentPagerAdapter;

import butterknife.BindView;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tl_main)
    TabLayout tlMainActivity;
    @BindView(R.id.vp_main)
    ViewPager vpMainActivity;
    @BindView(R.id.fab_main)
    FloatingActionButton fabMain;

    private int currentIndex;

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
        vpMainActivity.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEventMainThread(FloatActionBarEvent event) {
        fabMain.setVisibility(event.show ? View.VISIBLE : View.GONE);
    }


    @OnClick({R.id.iv_refresh, R.id.fab_main})
    public void onClick(View view) {
        OrderFragmentPagerAdapter adapter = (OrderFragmentPagerAdapter) vpMainActivity.getAdapter();
        switch (view.getId()) {
            case R.id.iv_refresh:
                showToast("刷新");
                adapter.refreshFragment(currentIndex);
                break;
            case R.id.fab_main:
                adapter.scrollFragment(currentIndex);
                break;
        }
    }
}
