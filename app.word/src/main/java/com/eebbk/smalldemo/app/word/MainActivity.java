package com.eebbk.smalldemo.app.word;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import net.wequick.small.Small;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Small.openUri("main/main2", MainActivity.this);
            }
        });
    }
}
