package com.eebbk.smalldemo;

import android.app.Application;
import android.widget.Toast;

import net.wequick.small.Small;

/**
 * Author: halo_huang
 * Create: 2019/2/19
 * Describe:
 */
public class MyApplication extends Application {

    public MyApplication() {
        Small.preSetUp(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Small.setLoadFromAssets(BuildConfig.LOAD_FROM_ASSETS);
        Small.setUp(this, new Small.OnCompleteListener() {
            @Override
            public void onComplete() {
                Toast.makeText(MyApplication.this, "Small setUp complete", Toast.LENGTH_LONG).show();
            }
        });
    }
}
