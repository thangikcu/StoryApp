package com.junior.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.junior.myapplication.fragment.StoryListFragment;
import com.junior.myapplication.fragment.StoryListFragmentOline;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private Fragment[] fragments;

    public PagerAdapter(FragmentManager fragmentManagerm) {
        super(fragmentManagerm);
    }

    public PagerAdapter(FragmentManager supportFragmentManager, Fragment[] fragments) {
        super(supportFragmentManager);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments[position];
    }

    @Override
    public int getCount() {
        if (fragments == null) return 0;
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Đọc truyện online";
                break;
            case 1:
                title = "Tủ truyện";
                break;
        }
        return title;
    }

}