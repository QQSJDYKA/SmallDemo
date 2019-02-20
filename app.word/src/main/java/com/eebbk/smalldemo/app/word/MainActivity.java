package com.eebbk.smalldemo.app.word;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.eebbk.smalldemo.app.word.bean.BundleJsonBean;
import com.google.gson.Gson;

import net.wequick.small.Small;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        findViewById(R.id.button_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final OkHttpClient httpClient = new OkHttpClient();
                final Request request = new Request.Builder()
                        .url("https://github.com/QQSJDYKA/SmallDemo/raw/master/update/bundle.json")
                        .method("GET", null)
                        .build();
                Call call = httpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        Gson gson = new Gson();
                        BundleJsonBean bundleJsonBean = gson.fromJson(response.body().string(), BundleJsonBean.class);
                        if (bundleJsonBean.getManifest() != null) {
                            if (Small.updateManifest(bundleJsonBean.getManifest(), false)) {
                                Log.i("haloq", "更新失败");
                                return;
                            }
                        }
                        for (final BundleJsonBean.UpdatesBean bean : bundleJsonBean.getUpdates()) {
                            Request request1 = new Request.Builder()
                                    .url(bean.getUrl())
                                    .get().build();
                            httpClient.newCall(request1)
                                    .enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {
                                            Log.i("haloq", bean.getPkg() + "更新失败");
                                        }

                                        @Override
                                        public void onResponse(Call call, Response response) throws IOException {
                                            net.wequick.small.Bundle bundle = Small.getBundle(bean.getPkg());
                                            File file = bundle.getPatchFile();
                                            InputStream inputStream = response.body().byteStream();
                                            OutputStream os = new FileOutputStream(file);
                                            byte[] buffer = new byte[1024];
                                            int length;
                                            while ((length = inputStream.read(buffer)) != -1) {
                                                os.write(buffer, 0, length);
                                            }
                                            os.flush();
                                            os.close();
                                            inputStream.close();
                                            bundle.upgrade();
                                            Log.i("haloq", bean.getPkg() + "更新完成");
                                        }
                                    });
                        }
                    }
                });

            }
        });
    }
}
