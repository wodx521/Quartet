package com.lr.quartetplatform;

import com.lr.quartetplatform.bean.GoodsInfoBean;

import java.util.Comparator;

public class ViewDownSort implements Comparator<GoodsInfoBean> {
    @Override
    public int compare(GoodsInfoBean o1, GoodsInfoBean o2) {
        try {
            int view2 = Integer.parseInt(o2.getPageView());
            int view1 = Integer.parseInt(o1.getPageView());
            return view2 - view1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}