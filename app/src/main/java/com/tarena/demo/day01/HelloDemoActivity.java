package com.tarena.demo.day01;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HelloDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_demo);
        Context context = this;
        // context.startActivity(null);
        // context.startService(null);
        // //?? 权限 位置？？？？
        // context.getCacheDir();
        // context.getDir(name, MODE_PRIVATE)
        // context.getDatabasePath(name)
        // context.getExternalCacheDir()
        // context.getExternalFilesDir(type);
        // context.getSystemService(name)

        // 上下文
        // Context context

        View testView = new View(context);
    }
}
