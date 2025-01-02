package com.example.productservice.controllers;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Products")
public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InstanceNotFoundException, ProductNotFoundException {
        Product product = null;
        //try {
            product = productService.getProductById(id);
        //} catch (InstanceNotFoundException e) {
        //    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //}
        ResponseEntity<Product> responseEntity;
//        if(product == null){
//            responseEntity = new ResponseEntity<>("Product not found for Id:"+ id, HttpStatus.NOT_FOUND);
//            return responseEntity;
//        }

        responseEntity = new ResponseEntity<>(product, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return  productService.updateProduct(id, product);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable("id") Long id, @RequestBody Product product) throws ProductNotFoundException {
        Product product1 =  productService.patchProduct(id, product);
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id){
        Product product = productService.delete(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        var response = productService.create(product);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    private ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException e){
//        ProductNotFoundExceptionDto productNotFoundExceptionDto = new ProductNotFoundExceptionDto();
//        productNotFoundExceptionDto.setErrorCode(e.getId());
//        productNotFoundExceptionDto.setErrorMessage(e.getMessage());
//        return new ResponseEntity<>(productNotFoundExceptionDto, HttpStatus.NOT_FOUND);
//    }


}
