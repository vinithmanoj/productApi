package com.example.products.service.impl;

import com.example.products.entity.Product;
import com.example.products.exceptions.DuplicateProductException;
import com.example.products.exceptions.ProductNotFoundException;
import com.example.products.repository.ProductRepository;
import com.example.products.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product create(Product product) {
        checkIfProductExists(product);
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(()->new ProductNotFoundException("Product not found with id-"+id) );
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    private void checkIfProductExists(Product product) {
        List<Product> existProducts = productRepository.findByNameContainingIgnoreCaseAndRate(product.getName(), product.getRate());
        if(!existProducts.isEmpty()){
            throw new DuplicateProductException("Product with same name and rate exists");
        }
    }
}
