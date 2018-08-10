package com.zither.aiiage.photolistproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import org.jetbrains.annotations.Nullable;

/**
 * @author wangyanqin
 */
public abstract class BaseActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_clear_memory_cache:
                ImageLoader.getInstance().clearMemoryCache();
                Toast.makeText(this, "清除内存缓存成功！", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_clear_disc_cache:
                ImageLoader.getInstance().clearDiskCache();
                Toast.makeText(this, "清除磁盘缓存成功", Toast.LENGTH_SHORT).show();
                break;
            default:
                return false;
        }
        return super.onOptionsItemSelected(item);
    }
}
