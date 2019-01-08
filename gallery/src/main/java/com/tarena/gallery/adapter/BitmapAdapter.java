package com.tarena.gallery.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tarena.gallery.R;
import com.tarena.gallery.entity.BitmapInfo;

import java.util.ArrayList;

public class BitmapAdapter extends BaseAdapter {
	private ArrayList<BitmapInfo> infos;
	private LayoutInflater inflater;

	public void setInfos(ArrayList<BitmapInfo> infos) {
		if (infos != null)
			this.infos = infos;
		else
			this.infos = new ArrayList<BitmapInfo>();
	}

	public BitmapAdapter(Context context, ArrayList<BitmapInfo> infos) {
		this.setInfos(infos);
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return infos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		// 加载和重用item视图
		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.ivThumb = (ImageView) convertView.findViewById(R.id.ivThumb);
			holder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// 获取指定位置的数据
		BitmapInfo info = infos.get(position);
		// 数据写入到item视图
		holder.ivThumb.setImageBitmap(info.getThumb());
		holder.tvTitle.setText(info.getTitle());
		// 返回item视图
		return convertView;
	}

	class ViewHolder {
		private ImageView ivThumb;
		private TextView tvTitle;
	}
}
