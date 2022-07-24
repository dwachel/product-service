package com.demo.ppv.product.domain.port.outbound;

import com.demo.ppv.product.domain.model.Product;

public interface ProductProvider {
    Product fetch(String id);
}
