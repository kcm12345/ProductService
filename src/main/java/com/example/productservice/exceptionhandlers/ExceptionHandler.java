package com.example.productservice.exceptionhandlers;

import com.example.productservice.dtos.ProductNotFoundExceptionDto;
import com.example.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFoundException.class)
    private ResponseEntity<ProductNotFoundExceptionDto> handleProductNotFoundException(ProductNotFoundException e){
        ProductNotFoundExceptionDto productNotFoundExceptionDto = new ProductNotFoundExceptionDto();
        productNotFoundExceptionDto.setErrorCode(e.getId());
        productNotFoundExceptionDto.setErrorMessage(e.getMessage());
        return new ResponseEntity<>(productNotFoundExceptionDto, HttpStatus.NOT_FOUND);
    }
}
