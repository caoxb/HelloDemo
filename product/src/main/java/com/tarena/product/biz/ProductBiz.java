package com.tarena.product.biz;

import com.tarena.product.entity.Product;

import java.util.ArrayList;
import java.util.Random;

public class ProductBiz {
    private ArrayList<Product> products;
    private Random rand;

    public ProductBiz() {
        products = new ArrayList<Product>();
        rand = new Random();
        for (int i = 1; i <= 20; i++) {
            Product product = new Product();
            product.setId(i);
            product.setName("商品" + i);
            product.setPrice(rand.nextInt(1000) + 300);
            product.setDescript("特价商品");

            products.add(product);
        }

    }

    /**
     * 返回所有的商品信息
     *
     * @return
     */
    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        if (product != null)
            products.add(product);
    }

    public void removeProduct(int position) {
        if (position >= 0 && position < products.size()) {
            products.remove(position);
        }
    }

    public void modifyProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getId() == product.getId()) {
                products.set(i, product);
            }
        }
    }

}
