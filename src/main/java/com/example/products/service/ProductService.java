package com.example.products.service;

import com.example.products.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    Product getProductById(Long id);

    List<Product> getAllProducts();
}
