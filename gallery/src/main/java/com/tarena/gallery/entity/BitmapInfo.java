package com.tarena.gallery.entity;

import android.graphics.Bitmap;

public class BitmapInfo {
	private String path;
	private String title;
	private Bitmap thumb;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Bitmap getThumb() {
		return thumb;
	}
	public void setThumb(Bitmap thumb) {
		this.thumb = thumb;
	}
	
	
}
