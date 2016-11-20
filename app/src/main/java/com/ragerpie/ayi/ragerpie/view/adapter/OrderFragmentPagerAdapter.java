package com.ragerpie.ayi.ragerpie.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ragerpie.ayi.ragerpie.view.fragment.BaseFragment;
import com.ragerpie.ayi.ragerpie.view.fragment.HistoryFragment;
import com.ragerpie.ayi.ragerpie.view.fragment.TodayFragment;
import com.ragerpie.ayi.ragerpie.view.fragment.UnfinishedFragment;

/**
 * Created by WangBo on 2016/10/28.
 */

public class OrderFragmentPagerAdapter extends FragmentPagerAdapter {
    private BaseFragment[] fragments;

    public OrderFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new BaseFragment[]{new UnfinishedFragment(), new TodayFragment(), new HistoryFragment()};
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments[position].getTitle();
    }

    public void refreshFragment(int index) {
        fragments[index].refreshData();
    }

    public void scrollFragment(int index) {
        fragments[index].scrollFragment();
    }

    public void setFabStat(int index) {
        for (int i = 0; i < fragments.length; i++) {
            fragments[i].onFragmentHiddenChanged(index != i);
        }
    }

    public void gotoTodayOrder(int orderId) {
        TodayFragment todayFragment = (TodayFragment) fragments[1];
        todayFragment.scrollToOrder(orderId);
    }
}
