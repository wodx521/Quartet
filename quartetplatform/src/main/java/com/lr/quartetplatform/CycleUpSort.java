package com.lr.quartetplatform;

import com.lr.quartetplatform.bean.GoodDetailBean;

import java.util.Comparator;

public class CycleUpSort implements Comparator<GoodDetailBean> {
    @Override
    public int compare(GoodDetailBean o1, GoodDetailBean o2) {
        try {
            int view2 = Integer.parseInt(o2.getDays());
            int view1 = Integer.parseInt(o1.getDays());
            return view1 - view2;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
