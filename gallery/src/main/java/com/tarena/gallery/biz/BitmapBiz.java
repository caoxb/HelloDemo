package com.tarena.gallery.biz;

import java.io.File;
import java.util.ArrayList;

import android.graphics.Bitmap;

import com.tarena.gallery.entity.BitmapInfo;
import com.tarena.gallery.utils.BitmapService;

public class BitmapBiz {
    public ArrayList<BitmapInfo> getBitmapInfos(String path) {
        ArrayList<BitmapInfo> infos = null;
        if (path != null) {
            // 根据传入的目录路径创建文件对象
            File dir = new File(path);
            // 如果该文件存在，且是目录
            if (dir.exists() && dir.isDirectory()) {
                // 初始化集合
                infos = new ArrayList<BitmapInfo>();
                // 获取该目录下文件名列表
                String[] files = dir.list();
                // 遍历文件
                for (String file : files) {
                    // 创建位图信息对象
                    BitmapInfo info = new BitmapInfo();
                    // 设置图片标题

                    info.setTitle(file.substring(0, file.lastIndexOf('.')));
                    // 设置图片路径
                    String picPath = path + file;
                    info.setPath(picPath);
                    // 根据路径获取缩略图，并设置缩略图
                    Bitmap thumb = BitmapService.getBitmap(info.getPath(), 80,
                            80);
                    info.setThumb(thumb);

                    // 添加到集合
                    infos.add(info);
                }
            }
        }
        // 返回集合
        return infos;
    }
}
