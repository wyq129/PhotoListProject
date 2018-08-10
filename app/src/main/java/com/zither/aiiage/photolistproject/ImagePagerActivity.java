package com.zither.aiiage.photolistproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * 单张图片可以执行左右滑动
 * @author wangyanqin
 * @date 2018/08/10
 */
public class ImagePagerActivity extends FragmentActivity {
    private static final String[] IMAGE_URLS = Constants.IMAGES;
    int position;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_pager);
        mViewPager = findViewById(R.id.activity_imagePager);
        FragmentManager manager = getSupportFragmentManager();
        Intent intent = getIntent();
        position = intent.getIntExtra(Constants.Extra.IMAGE_POSITION, -1);
        mViewPager.setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                String url = IMAGE_URLS[position];
                return ImageDetailFragment.newInstance(url);
            }

            @Override
            public int getCount() {
                return IMAGE_URLS.length;
            }
        });
        for (int i = 0; i < IMAGE_URLS.length; i++) {
            if (i == position) {
                mViewPager.setCurrentItem(position);
            }
        }
    }
}
