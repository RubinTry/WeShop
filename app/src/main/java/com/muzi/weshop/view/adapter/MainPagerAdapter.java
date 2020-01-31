package com.muzi.weshop.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> pageList;

    public MainPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> pageList) {
        super(fm, behavior);
        this.pageList = pageList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return pageList.get(position);
    }

    @Override
    public int getCount() {
        return pageList.size();
    }
}
