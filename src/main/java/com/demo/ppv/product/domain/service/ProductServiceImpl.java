package com.demo.ppv.product.domain.service;

import com.demo.ppv.product.domain.model.Product;
import com.demo.ppv.product.domain.port.inbound.ProductService;
import com.demo.ppv.product.domain.port.outbound.ProductProvider;

public class ProductServiceImpl implements ProductService {

    private ProductProvider productProvider;

    @Override
    public Product getProduct(String id) {
        var product = productProvider.fetch(id);

        /*
         * other business logic required to get product, like checking permissions
         */

        return product;
    }
}
