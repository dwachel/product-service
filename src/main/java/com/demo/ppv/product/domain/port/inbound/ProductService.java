package com.demo.ppv.product.domain.port.inbound;

import com.demo.ppv.product.domain.model.Product;

public interface ProductService {
    Product getProduct(String id);
}
