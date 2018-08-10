package com.zither.aiiage.photolistproject;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

/**
 * @author wangyanqin
 * @date 2018/08/10
 */
public class MainActivity extends AbsListViewBaseActivity {
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        listView = findViewById(R.id.gridview);
        listView.setAdapter(new ImageAdapter(mContext));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //startImagePagerActivity(i);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContext = null;
    }
}
