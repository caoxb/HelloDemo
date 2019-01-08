package com.tarena.product;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tarena.product.entity.Product;
import com.tarena.product.utils.GlobalUtils;

public class ProductOpActivity extends Activity {
	private EditText etId, etName, etDescriptioin, etPrice;
	private Button btnOp;
	private int opType;

	private void setupView() {
		etDescriptioin = (EditText) findViewById(R.id.etDestription);
		etId = (EditText) findViewById(R.id.etId);
		etPrice = (EditText) findViewById(R.id.etPrice);
		etName = (EditText) findViewById(R.id.etName);

		btnOp = (Button) findViewById(R.id.btnOp);

		switch (opType) {
			case GlobalUtils.OP_TYPE_ADD:// 操作类型为添加
				btnOp.setText("添加");
				etId.setEnabled(true);
				break;

			case GlobalUtils.OP_TYPE_UPDATE:// 操作类型为修改
				btnOp.setText("修改");
				etId.setEnabled(false);
				Product product = (Product) getIntent().getSerializableExtra(
						GlobalUtils.EXTRA_OP_DATA);
				if (product != null) {
					etId.setText("" + product.getId());
					etName.setText(product.getName());
					etDescriptioin.setText(product.getDescript());
					etPrice.setText(product.getPrice() + "");
				}
				break;
		}
	}

	public void doClick(View v) {
		// 获取用户输入，并封装为实体对象
		Product product = new Product();
		product.setId(Integer.parseInt(etId.getText().toString()));
		product.setName(etName.getText().toString());
		product.setDescript(etDescriptioin.getText().toString());
		product.setPrice(Float.parseFloat(etPrice.getText().toString()));
		// 将该实体对象作为返回值返回到 源Activity
		Intent intent = new Intent();
		intent.putExtra(GlobalUtils.EXTRA_OP_DATA, product);
		intent.putExtra(GlobalUtils.EXTRA_OP_TYPE, opType);
		// 设置返回值
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.product_op);

		opType = getIntent().getIntExtra(GlobalUtils.EXTRA_OP_TYPE,
				GlobalUtils.OP_TYPE_ADD);
		setupView();
	}
}
