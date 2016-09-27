package com.creater.jiecaovideodemo;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class MainActivity extends AppCompatActivity {



    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        LinearLayout view1 =(LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_video,null,false);
        LinearLayout view2 =(LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_video,null,false);
        LinearLayout view3 =(LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_video,null,false);
        LinearLayout view4 =(LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_video,null,false);

        final ArrayList<View> viewList=new ArrayList();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view2);
        viewList.add(view2);

        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view =LayoutInflater.from(MainActivity.this).inflate(R.layout.item_video,container,false);
                container.addView(view);
                return view;
            }
        });
        JCVideoPlayerStandard myVideo=new JCVideoPlayerStandard(this);
        myVideo.setUp("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4"
                , JCVideoPlayerStandard.SCREEN_LAYOUT_LIST, "嫂子闭眼睛");
        Picasso.with(this).load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").into(myVideo.thumbImageView);

       // mViewPager.addView(myVideo);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
