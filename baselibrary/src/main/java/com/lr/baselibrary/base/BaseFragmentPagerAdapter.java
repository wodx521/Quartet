package com.lr.baselibrary.base;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    protected List<BaseFragment> list = new ArrayList<>();
    protected List<String> listTab = new ArrayList<>();

    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(BaseFragment baseFragment, String tab) {
        listTab.add(tab);
        list.add(baseFragment);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // 防止销毁fragment
    }

    @NotNull
    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }
}
