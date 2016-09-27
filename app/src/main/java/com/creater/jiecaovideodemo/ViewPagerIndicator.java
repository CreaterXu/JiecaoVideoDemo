package com.creater.jiecaovideodemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;



/**
 * Created by Administrator on 2016/9/27.
 */

public class ViewPagerIndicator extends View implements ViewPager.OnPageChangeListener{
    private Drawable mIndicator;

    public ViewPagerIndicator(Context context) {
        this(context,null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //通过 TypedArray 获取自定义属性
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.ActionBar);
        //获取自定义属性的个数
        int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = typedArray.getIndex(i);
//            switch (attr) {
//                case R.styleable.IndicatorView_indicator_icon:
//                    //通过自定义属性拿到指示器
//                    mIndicator = typedArray.getDrawable(attr);
//                    break;
//                case R.styleable.IndicatorView_indicator_margin:
//                    float defaultMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5,getResources().getDisplayMetrics());
//                    mMargin = (int) typedArray.getDimension(attr , defaultMargin);
//                    break ;
//                case R.styleable.IndicatorView_indicator_smooth:
//                    mSmooth = typedArray.getBoolean(attr,false) ;
//                    break;
         //   }
        }
        //使用完成之后记得回收
        //typedArray.recycle();
        //initIndicator() ;
    }
    private void initIndicator() {
        //获取指示器的大小值。一般情况下是正方形的，也是时，你的美工手抖了一下，切出一个长方形来了，
        //不用怕，这里做了处理不会变形的
       // mIndicatorSize = Math.max(mIndicator.getIntrinsicWidth(),mIndicator.getIntrinsicHeight()) ;
        /*设置指示器的边框*/
        mIndicator.setBounds(0,0,mIndicator.getIntrinsicWidth(),mIndicator.getIntrinsicWidth());
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
