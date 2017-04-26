package com.pengzhangdemo.com.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class LiveMessagePageAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = null;
    private String[] titleList = null;

    public LiveMessagePageAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);

        fragmentList = fragments;
    }

    public LiveMessagePageAdapter(FragmentManager fm, List<Fragment> fragments, String[] titles) {
        super(fm);

        fragmentList = fragments;
        titleList = titles;
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titleList != null && titleList.length > 0)
            return titleList[position];
        return null;
    }

}
