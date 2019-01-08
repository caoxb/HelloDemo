package com.tarena.product;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tarena.product.adapter.ProductAdapter;
import com.tarena.product.biz.ProductBiz;
import com.tarena.product.entity.Product;
import com.tarena.product.utils.GlobalUtils;

public class ProductActivity extends AppCompatActivity {
    private static final int MENU_OPTS_ADD = 1;
    private static final int MENU_OPTS_EXIT = 2;
    private static final int MENU_CONTEXT_UPDATE = 20;
    private static final int MENU_CONTEXT_DELETE = 21;

    private ListView lvProducts;
    private ProductBiz biz;
    private ProductAdapter adapter;

    private void setupView() {
        lvProducts = (ListView) findViewById(R.id.lvProducts);
        adapter = new ProductAdapter(this, biz.getProducts());
        lvProducts.setAdapter(adapter);
    }

    private void addListener() {
        lvProducts.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v,
                                                    ContextMenu.ContextMenuInfo menuInfo) {
                        // TODO Auto-generated method stub
                        menu.setHeaderIcon(R.mipmap.ic_launcher);
                        menu.setHeaderTitle("操作");
                        menu.add(2, MENU_CONTEXT_UPDATE, 1, "修改");
                        menu.add(2, MENU_CONTEXT_DELETE, 2, "删除");
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        biz = new ProductBiz();
        setupView();
        addListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        menu.add(1, MENU_OPTS_ADD, 1, "添加").setIcon(
                android.R.drawable.ic_menu_add);
        menu.add(1, MENU_OPTS_EXIT, 2, "退出").setIcon(
                android.R.drawable.ic_menu_close_clear_cancel);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case MENU_OPTS_ADD:// 添加
                Intent intent = new Intent(this, ProductOpActivity.class);
                intent.putExtra(GlobalUtils.EXTRA_OP_TYPE, GlobalUtils.OP_TYPE_ADD);
                startActivityForResult(intent, 0);
                break;
            case MENU_OPTS_EXIT:// 退出
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 当目标Activity返回值时回调 的方法
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        // 如果结果码为 RESULT_OK,则返回值成功
        if (resultCode == RESULT_OK) {
            // 获取返回的商品信息
            Product product = (Product) data
                    .getSerializableExtra(GlobalUtils.EXTRA_OP_DATA);
            // 获取返回的 操作类型
            int opType = data.getIntExtra(GlobalUtils.EXTRA_OP_TYPE,
                    GlobalUtils.OP_TYPE_ADD);
            // 根据操作类型执行相关操作
            switch (opType) {
                case GlobalUtils.OP_TYPE_ADD:
                    biz.addProduct(product);
                    break;

                case GlobalUtils.OP_TYPE_UPDATE:
                    biz.modifyProduct(product);
                    break;
            }
            // 通知ListView重绘界面
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        // 当导致上下文菜单弹出的事件源是AdaperView时，可以将item中的MenuInfo对象强转为AdapterCotextMenuInfo
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();
        // 获取被长按的item的position
        int position = menuInfo.position;

        switch (item.getItemId()) {
            case MENU_CONTEXT_UPDATE:// 修改
                Intent intent = new Intent(this, ProductOpActivity.class);
                intent.putExtra(GlobalUtils.EXTRA_OP_TYPE,
                        GlobalUtils.OP_TYPE_UPDATE);
                // 根据位置，从adapter中获取指定位置的数据
                Product product = (Product) adapter.getItem(position);
                intent.putExtra(GlobalUtils.EXTRA_OP_DATA, product);
                startActivityForResult(intent, 0);
                break;
            case MENU_CONTEXT_DELETE:// 删除
                biz.removeProduct(position);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }
}
