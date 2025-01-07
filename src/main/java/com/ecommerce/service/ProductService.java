package com.ecommerce.service;

import com.ecommerce.payload.ProductDto;
import com.ecommerce.payload.ProductResponse;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto, Long categoryId);

    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductResponse getAllProductsByCategory(Long categoryId);

    ProductResponse getAllProductsByKeyword(String keyword);

    ProductDto updateProduct(ProductDto productDto, Long productId);

    ProductDto deleteProduct(Long productId);
}
