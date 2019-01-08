package com.tarena.gallery;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.tarena.gallery.adapter.BitmapAdapter;
import com.tarena.gallery.biz.BitmapBiz;
import com.tarena.gallery.entity.BitmapInfo;
import com.tarena.gallery.utils.BitmapService;
import com.tarena.gallery.utils.PermissionUtil;

public class GalleryActivity extends AppCompatActivity {
    private static final String PATH = Environment.getExternalStorageDirectory() + "/imgs/";
    private ImageSwitcher isPic;
    private Gallery galThumbs;
    private BitmapBiz biz;
    private BitmapAdapter adapter;
    private int currentIndex;
    private GestureDetector detector;

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            // TODO Auto-generated method stub
            // 取当前选中项的索引
            int currentPosition = galThumbs.getSelectedItemPosition();

            if (e1.getX() - e2.getX() > 20 && Math.abs(velocityX) > 100) {
                // 从右向左,下一幅
                if (++currentPosition > galThumbs.getCount() - 1) {
                    // 如果索引右越界，则索引设定为最后一幅图片的索引
                    currentPosition = galThumbs.getCount() - 1;
                    Toast.makeText(GalleryActivity.this,
                            "已经是最后一幅", Toast.LENGTH_SHORT).show();
                }

            } else if (e2.getX() - e1.getX() > 20 && Math.abs(velocityX) > 100) {
                // 从左向右,上一幅
                if (--currentPosition < 0) {
                    currentPosition = 0;
                    Toast.makeText(GalleryActivity.this,
                            "已经是第一幅", Toast.LENGTH_SHORT).show();
                }

            }

            // 设置新的currentPosition为选中项
            galThumbs.setSelection(currentPosition);
            return super.onFling(e1, e2, velocityX, velocityY);
        }

    }

    private void setupView() {
        isPic = (ImageSwitcher) findViewById(R.id.isPic);
        isPic.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                // TODO Auto-generated method stub
                ImageView iv = new ImageView(
                        GalleryActivity.this);
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                iv.setLayoutParams(params);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                return iv;
            }
        });

        galThumbs = (Gallery) findViewById(R.id.galThumb);
        adapter = new BitmapAdapter(this, biz.getBitmapInfos(PATH));
        galThumbs.setAdapter(adapter);
    }

    private void addListener() {
        galThumbs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                // TODO Auto-generated method stub
                if (position > currentIndex) {
                    // 下一幅
                    isPic.setInAnimation(
                            GalleryActivity.this,
                            R.anim.right_in);
                    isPic.setOutAnimation(
                            GalleryActivity.this,
                            R.anim.left_out);
                } else if (position < currentIndex) {
                    // 上一幅
                    isPic.setInAnimation(
                            GalleryActivity.this,
                            R.anim.left_in);
                    isPic.setOutAnimation(
                            GalleryActivity.this,
                            R.anim.right_out);
                }

                currentIndex = position;
                // g根据选中项的位置，获取选中项的数据
                BitmapInfo info = (BitmapInfo) adapter.getItem(position);
                // 根据图片路径加载图片对象
                Bitmap bm = BitmapService.getBitmap(info.getPath(), 2);
                // 设置图片显示在imageSwitcher中
                isPic.setImageDrawable(new BitmapDrawable(bm));
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
        if (PermissionUtil.checkPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, R.id.galThumb % 4096)) {
            biz = new BitmapBiz();
            setupView();
            detector = new GestureDetector(new MyGestureListener());
            addListener();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        return detector.onTouchEvent(event);
    }
}
