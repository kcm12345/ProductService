package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.projections.ProductTitleAndDescription;
import com.example.productservice.repos.CategoryRepo;
import com.example.productservice.repos.ProductRepo;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.List;

@Service("RealProductService")
public class RealProductService implements ProductService{

    private final ProductRepo _productRepo;
    private final CategoryRepo categoryRepo;

    public RealProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        _productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }
    @Override
    public Product getProductById(Long id) throws InstanceNotFoundException, ProductNotFoundException {
        ProductTitleAndDescription productTitleAndDescription = _productRepo.getProductTitleAndDesc(id);
        System.out.println("HQL=>Product: title-" + productTitleAndDescription.getTitle() + " description-" + productTitleAndDescription.getDescription());
        productTitleAndDescription = _productRepo.getProductTitleAndDescSQL(id);
        System.out.println("SQL=>Product: title-" + productTitleAndDescription.getTitle() + " description-" + productTitleAndDescription.getDescription());
        return _productRepo.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product patchProduct(Long id, Product product) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Product delete(Long id) {
        return null;
    }

    @Override
    public  Product create(Product product){
        Category category = product.getCategory();
        if(category.getId() == null){
            Category savedCategory = categoryRepo.save(category);
            product.setCategory(savedCategory);
        }
        else {
            // category validation logic
        }
        return _productRepo.save(product);
    }
}
