package com.eebbk.smalldemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import net.wequick.small.Small;

public class LauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Small.openUri("word", LauncherActivity.this);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
