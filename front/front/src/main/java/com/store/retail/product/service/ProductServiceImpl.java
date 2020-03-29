package com.store.retail.product.service;

import com.store.retail.product.model.Product;
import com.store.retail.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<Product> readProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product readProductById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Optional<List<Product>> readProductByName(String name){
        return this.productRepository.findByName(name);
    }
}
