package com.ragerpie.ayi.ragerpie.view.activity;

import android.app.DatePickerDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;

import com.ragerpie.ayi.ragerpie.R;
import com.ragerpie.ayi.ragerpie.config.Constants;
import com.ragerpie.ayi.ragerpie.event.DatePickedEvent;
import com.ragerpie.ayi.ragerpie.event.FloatActionCalenderBtn;
import com.ragerpie.ayi.ragerpie.event.FloatActionScrollEvent;
import com.ragerpie.ayi.ragerpie.util.LogUtils;
import com.ragerpie.ayi.ragerpie.view.adapter.OrderFragmentPagerAdapter;

import java.util.Calendar;

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
    @BindView(R.id.fab_scroll)
    FloatingActionButton fabScroll;
    @BindView(R.id.fab_calender)
    FloatingActionButton fabCalender;

    private OrderFragmentPagerAdapter pagerAdapter;
    private int currentIndex;
    private Calendar calendar;

    @Override
    protected void initVariable() {
        pagerAdapter = new OrderFragmentPagerAdapter(getSupportFragmentManager());
        calendar = Calendar.getInstance();
    }

    @Override
    protected void initView() {
        toolbar.setTitle("RagerPie");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        tlMainActivity.addTab(tlMainActivity.newTab());
        tlMainActivity.addTab(tlMainActivity.newTab());
        tlMainActivity.addTab(tlMainActivity.newTab());
        vpMainActivity.setAdapter(pagerAdapter);
        vpMainActivity.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMainActivity));
        vpMainActivity.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
                if (currentIndex != 2) {
                    fabCalender.setVisibility(View.GONE);
                } else {
                    fabCalender.setVisibility(View.VISIBLE);
                }
                pagerAdapter.setFabStat(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void loadData() {
        checkOrderStatChangeNotifyClicked();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void checkOrderStatChangeNotifyClicked() {
        int orderId = getIntent().getIntExtra(Constants.EXTRA_GOTO_TODAY_ORDER, -1);
        if (orderId != -1) {
            pagerAdapter.gotoTodayOrder(orderId);
        }
    }

    public void onEventMainThread(FloatActionScrollEvent event) {
        fabScroll.setVisibility(event.show ? View.VISIBLE : View.GONE);
    }

    public void onEventMainThread(FloatActionCalenderBtn event) {
        if (currentIndex == 2) {
            fabCalender.setVisibility(event.show ? View.VISIBLE : View.GONE);
        }
    }

    @OnClick({R.id.iv_refresh, R.id.fab_scroll, R.id.fab_calender})
    public void onClick(View view) {
        OrderFragmentPagerAdapter adapter = (OrderFragmentPagerAdapter) vpMainActivity.getAdapter();
        switch (view.getId()) {
            case R.id.iv_refresh:
                showToast("刷新");
                adapter.refreshFragment(currentIndex);
                break;
            case R.id.fab_scroll:
                adapter.scrollFragment(currentIndex);
                break;
            case R.id.fab_calender:
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker,
                                                  int year,
                                                  int monthOfYear,
                                                  int dayOfMonth) {
                                LogUtils.d("datePicked:" + year + monthOfYear + dayOfMonth);
                                EventBus.getDefault().post(new DatePickedEvent(year, monthOfYear, dayOfMonth));
                                calendar.set(year, monthOfYear - 1, dayOfMonth);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
                break;
        }
    }

}
