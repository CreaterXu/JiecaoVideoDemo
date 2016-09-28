package com.creater.widgt;

import android.support.v4.view.ViewPager;
import android.view.View;

/*
*页面指示接口
*@authur Creater
*create at 2016/9/28 14:03
*/
interface PagerIndicator extends ViewPager.OnPageChangeListener{
    /**
    *观察数据变化
    *create at 2016/9/28 14:02
    */
    void notifyDataChanged();
    /**
    *绑定Viewpager
     * @param viewPager
    *create at 2016/9/28 14:02
    */
    void setViewPager(ViewPager viewPager);
    /**
     * 根据初始位置绑定Viewpager
     * @param viewPager
     * @param initPosition
     * */
    void setViewPager(ViewPager viewPager,int initPosition);
    /**
    *设置当前view
    *@param position
    *create at 2016/9/28 13:59
    */
    void setCurrentItem(int position);
    /**
     * 设置page改变的监听器
    *@param listener
    *create at 2016/9/28 15:02
    */
    void setPageChangeListener(ViewPager.OnPageChangeListener listener);
}
