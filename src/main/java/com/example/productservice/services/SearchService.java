package com.example.productservice.services;

import com.example.productservice.models.Product;
import com.example.productservice.repos.ProductRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    private final ProductRepo productRepo;
    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public List<Product> search(String keyword, int pageNumber, int pageSize, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("asc") ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();
        return productRepo.findByTitleContainsIgnoreCase(keyword, PageRequest.of(pageNumber, pageSize, sort));
    }
}
