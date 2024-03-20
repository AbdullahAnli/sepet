package com.sepet.service;

import com.sepet.entity.Product;
import com.sepet.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product>getAllProduct(){
        return productRepository.findAll();
    }
    public Optional<Product>getByProductId(Long id){
        return productRepository.findById(id);
    }
    public Product createProduct(Product product){
        return productRepository.save(product);
    }
    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
