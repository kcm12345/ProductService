package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreProductDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import javax.management.InstanceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FakeStoreProductService implements  ProductService{
    RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);
        if(fakeStoreProductDto == null) {
            throw  new ProductNotFoundException(100L, "Product not found with id " + id);
        }
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        if (fakeStoreProductDtos != null) {
            for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
                products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
            }
        }

        return  products;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = getFakeStoreProductDto(product);
        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto1 = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor)
                .getBody();
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto1);
    }

    private static FakeStoreProductDto getFakeStoreProductDto(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getTitle());
        }
        return fakeStoreProductDto;
    }

    @Override
    public Product patchProduct(Long id, Product product) throws ProductNotFoundException {
        FakeStoreProductDto fakeStoreProductDto = getFakeStoreProductDto(product);
        /*HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(product);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.PATCH,
                httpEntity,
                FakeStoreProductDto.class,
                id
        );*/


        FakeStoreProductDto response = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id, product, FakeStoreProductDto.class);
        System.out.println("Response: " + response);
        return convertFakeStoreProductDtoToProduct(response );



/*        if(response.getStatusCode() == HttpStatus.OK)
        return convertFakeStoreProductDtoToProduct(response.getBody());
        else{
            throw new ProductNotFoundException(id, "Patch is failed for id:{id}");
        }*/
    }

    @Override
    public Product delete(Long id) {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + id,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class,
                id);
        return convertFakeStoreProductDtoToProduct(response.getBody());
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        if(fakeStoreProductDto == null) {
            return null;
        }

        Product product = new Product();

        // Map basic fields
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());

        // Convert category (create a new Category object)
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}
