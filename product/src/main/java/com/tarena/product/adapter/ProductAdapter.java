package com.tarena.product.adapter;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tarena.product.R;
import com.tarena.product.entity.Product;

public class ProductAdapter extends BaseAdapter {
	private ArrayList<Product> products;
	private LayoutInflater inflater;

	public void setProducts(ArrayList<Product> products) {
		if (products != null)
			this.products = products;
		else
			this.products = new ArrayList<Product>();
	}

	public ProductAdapter(Context context, ArrayList<Product> products) {
		this.setProducts(products);
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return products.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return products.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return products.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.tvId = (TextView) convertView.findViewById(R.id.tvId);
			holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
			holder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);
			holder.tvDescript = (TextView) convertView
					.findViewById(R.id.tvDescript);
			convertView.setTag(holder);

		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		Product product = products.get(position);

		holder.tvId.setText(product.getId() + "");
		holder.tvName.setText(product.getName());
		holder.tvPrice.setText(product.getPrice() + "");
		holder.tvDescript.setText(product.getDescript());

		return convertView;
	}

	class ViewHolder {
		private TextView tvId;
		private TextView tvName;
		private TextView tvPrice;
		private TextView tvDescript;
	}
}
