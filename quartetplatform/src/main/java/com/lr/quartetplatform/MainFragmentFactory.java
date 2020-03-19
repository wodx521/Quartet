package com.lr.quartetplatform;

import android.util.SparseArray;

import com.lr.baselibrary.base.BaseFragment;
import com.lr.quartetplatform.moudle1.FirstFragment;
import com.lr.quartetplatform.moudle2.SecondFragment;
import com.lr.quartetplatform.moudle3.ThirdFragment;
import com.lr.quartetplatform.moudle4.FourFragment;

public class MainFragmentFactory {
    public static SparseArray<BaseFragment> fragmentMainMap = new SparseArray<>();

    public static BaseFragment getFragment(int position) {
        BaseFragment baseFragment = null;
        // 尝试从内存中读取需要的对象
        baseFragment = fragmentMainMap.get(position);
        if (baseFragment != null) {
            return baseFragment;
        } else {
            switch (position) {
                case 0:
                    baseFragment = new FirstFragment();
                    break;
                case 1:
                    baseFragment = new SecondFragment();
                    break;
                case 2:
                    baseFragment = new ThirdFragment();
                    break;
                case 3:
                    baseFragment = new FourFragment();
                    break;
                default:
            }
            fragmentMainMap.put(position, baseFragment);
            return baseFragment;
        }
    }
}
