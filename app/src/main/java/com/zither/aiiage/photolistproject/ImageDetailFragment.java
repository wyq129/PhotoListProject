package com.zither.aiiage.photolistproject;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import cn.bluemobi.dylan.photoview.library.PhotoViewAttacher;


/**
 * 点击其中一张图片放大
 * @author wangyanqin
 * @date 2018/08/10
 */
public class ImageDetailFragment extends android.support.v4.app.Fragment {
    private String mImageViewUrl;
    private SquareImageView mSquareImageView;
    private ProgressBar mProgressBar;
    //第三方开源库：PhotoView-master实现缩放的效果 compile 添加
    private PhotoViewAttacher mPhotoViewAttacher;

    public static ImageDetailFragment newInstance(String imageUrl) {
        ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
        final Bundle m = new Bundle();
        m.putString("url", imageUrl);
        imageDetailFragment.setArguments(m);
        return imageDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImageViewUrl = getArguments() != null ? getArguments().getString("url") : null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        mProgressBar = view.findViewById(R.id.progress_pagerImage);
        mSquareImageView = view.findViewById(R.id.squareImageView_pagerView);
        mPhotoViewAttacher = new PhotoViewAttacher(mSquareImageView);
        //在图片上点击则退出
        mPhotoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                getActivity().finish();
            }
        });
        return view;
    }

    //ImageLoader显示图片
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageLoader.getInstance().displayImage(mImageViewUrl, mSquareImageView, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                String message = null;
                switch (failReason.getType()) {
                    case IO_ERROR:
                        message = "下载错误";
                        break;
                    case DECODING_ERROR:
                        message = "图片无法显示";
                        break;
                    case NETWORK_DENIED:
                        message = "网络有问题，无法下载";
                        break;
                    case OUT_OF_MEMORY:
                        message = "图片太大无法显示";
                        break;
                    case UNKNOWN:
                        message = "未知的错误";
                        break;
                    default:
                }
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                mProgressBar.setVisibility(View.GONE);
                mPhotoViewAttacher.update();
            }
        });
    }
}
