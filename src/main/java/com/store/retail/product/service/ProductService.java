package com.store.retail.product.service;

import com.store.retail.product.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> readProducts();
    Product readProductById(Integer id);
    Optional<List<Product>> readProductByName(String name);
}
