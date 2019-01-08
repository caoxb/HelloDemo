package com.tarena.gallery.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class BitmapService {
	public static Bitmap getBitmap(String path) {
		return BitmapFactory.decodeFile(path);
	}

	public static Bitmap getBitmap(String path, int scale) {
		Options opts = new Options();
		opts.inSampleSize = scale;
		return BitmapFactory.decodeFile(path, opts);
	}

	public static Bitmap getBitmap(String path, int width, int height) {
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(path, opts);
		int x = opts.outWidth / width;
		int y = opts.outHeight / height;
		int scale = x > y ? x : y;
		return getBitmap(path, scale);
	}
}
