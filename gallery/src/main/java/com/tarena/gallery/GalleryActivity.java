package com.tarena.gallery;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageView;

import com.tarena.gallery.adapter.BitmapAdapter;
import com.tarena.gallery.biz.BitmapBiz;
import com.tarena.gallery.entity.BitmapInfo;
import com.tarena.gallery.utils.BitmapService;
import com.tarena.gallery.utils.PermissionUtil;

public class GalleryActivity extends AppCompatActivity {
    private static final String PATH = Environment.getExternalStorageDirectory()+"/imgs/";
    private ImageView ivPic;
    private Gallery galThumbs;
    private BitmapBiz biz;
    private BitmapAdapter adapter;

    private void setupView() {
        ivPic = (ImageView) findViewById(R.id.ivPic);

        galThumbs = (Gallery) findViewById(R.id.galThumb);
        adapter = new BitmapAdapter(this, biz.getBitmapInfos(PATH));
        galThumbs.setAdapter(adapter);
    }

    private void addListener() {
        galThumbs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // TODO Auto-generated method stub
                // g根据选中项的位置，获取选中项的数据
                BitmapInfo info = (BitmapInfo) adapter.getItem(position);
                // 根据图片路径加载图片对象
                Bitmap bm = BitmapService.getBitmap(info.getPath(), 2);
                // 设置图片显示在imageview中
                ivPic.setImageBitmap(bm);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }

        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        if (PermissionUtil.checkPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE,R.id.galThumb % 4096)) {
            biz = new BitmapBiz();
            setupView();
            addListener();
        }
    }
}
