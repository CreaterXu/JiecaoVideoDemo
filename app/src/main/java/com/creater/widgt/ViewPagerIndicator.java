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
    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPage=position;
        invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        if(state==ViewPager.SCROLL_STATE_DRAGGING){
//            setVisibility(GONE);
//        }
//        setVisibility(GONE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);

        int width=mWidth,height=mHeight, x=0,y=0, radius=0;
        int count=mViewPager.getAdapter().getCount();

        radius=height/4;
        if(2*count*radius>=width/2){
            radius=width/2/count/2;
        }
        x=width/2-count*radius+radius;
        y=radius*2;
        Log.e("xv","width:"+width+"  height:"+height+" x:"+x+" y:"+y);
        Paint paint=new Paint();
        for (int i=0;i<count;i++){
            if (i==mCurrentPage) {
                paint.setColor(Color.RED);
                canvas.drawCircle(x + i *2*radius, y, radius,paint);
            }
            else
            {
                paint.setColor(Color.WHITE);
                canvas.drawCircle(x + i*2*radius, y, radius,paint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mWidth=MeasureSpec.getSize(widthMeasureSpec);
        mHeight=MeasureSpec.getSize(heightMeasureSpec);
        int specWideMode=MeasureSpec.getMode(widthMeasureSpec);
        int specHeighMode=MeasureSpec.getMode(heightMeasureSpec);
        switch (specWideMode) {
            case MeasureSpec.UNSPECIFIED:
                Log.e("xv", "onMeasure: unspe");
                break;
            case MeasureSpec.AT_MOST:
                Log.e("xv", "onMeasure: At_most");
                break;
            case MeasureSpec.EXACTLY:
                Log.e("xv", "onMeasure: exactiy");
                break;
        }
        Log.e("xv","width:"+mWidth+"height:"+mHeight+"  widtmode:"+specWideMode+"  heighMode:"+specHeighMode);
        //setMeasuredDimension(mWidth,mHeight);
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
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
