package com.creater.widgt;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.creater.jiecaovideodemo.R;


/**
 * Created by Administrator on 2016/9/27.
 */

public class ViewPagerIndicator extends View implements PagerIndicator{
    private Drawable mPoint;
    private int mSize;//指示器个数
    private int mCurrentPage;//当前指示位置
    private ViewPagerIndicator mIndicator;//自己的引用
    private int mWidth;
    private int mHeight;
    private  ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mListener;

    public ViewPagerIndicator(Context context) {
        this(context,null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = getResources().obtainAttributes(attrs, R.styleable.ActionBar);
        int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            int attr = typedArray.getIndex(i);
            switch (attr){
                case R.styleable.ViewPagerIndicator_iconxv:
                    mPoint=typedArray.getDrawable(attr);
                break;
            }

        }
        mWidth=this.getWidth();
        mHeight=this.getHeight();
        //使用完成之后记得回收
        typedArray.recycle();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mCurrentPage=position;
        invalidate();
        setVisibility(VISIBLE);
    }

    @Override
    public void onPageSelected(int position) {
        Log.e("xv","chosen :"+position);
        mCurrentPage=position;
        invalidate();
        setVisibility(GONE);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x=40,y=5;
        Paint paint=new Paint();
        for (int i=0;i<mViewPager.getAdapter().getCount();i++){
            if (i==mCurrentPage) {
                paint.setColor(Color.RED);
                canvas.drawCircle(x + i * 20, y, 10,paint);
            }
            else
            {
                paint.setColor(Color.WHITE);
                canvas.drawCircle(x + i * 20, y, 10,paint);
            }
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public void notifyDataChanged() {
        invalidate();
    }

    @Override
    public void setViewPager(ViewPager viewPager) {
        if (mViewPager==viewPager){
            return;
        }
        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }
        if (viewPager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager = viewPager;
        mViewPager.setOnPageChangeListener(this);
        invalidate();
    }

    @Override
    public void setViewPager(ViewPager viewPager, int initPosition) {
        setViewPager(viewPager);
        setCurrentItem(initPosition);
    }

    @Override
    public void setCurrentItem(int position) {
        if (mViewPager==null){
            throw  new IllegalStateException("ViewPager is null");
        }
        mViewPager.setCurrentItem(position);
        mCurrentPage=position;
        invalidate();
    }

    @Override
    public void setPageChangeListener(ViewPager.OnPageChangeListener listener) {
        if (listener==null)
            mListener=this;
        else mListener=listener;
    }

}
