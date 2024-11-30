package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.springframework.http.ResponseEntity;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Map;

public interface ProductService {
    Product getProductById(Long id) throws InstanceNotFoundException, ProductNotFoundException;

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    Product patchProduct(Long id, Product product) throws ProductNotFoundException;

    Product delete(Long id);
}
