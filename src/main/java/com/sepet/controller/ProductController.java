package com.sepet.controller;

import com.sepet.entity.Product;
import com.sepet.service.ProductService;
import jakarta.servlet.http.PushBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @GetMapping("/{id}")
    public Optional<Product>getByIdProducts(@PathVariable Long id){
        return productService.getByProductId(id);
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
    @DeleteMapping
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
