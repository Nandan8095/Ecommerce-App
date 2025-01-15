package com.ecommerce.controller;

import com.ecommerce.configure.AppConstant;
import com.ecommerce.payload.ProductDto;
import com.ecommerce.payload.ProductResponse;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto, @PathVariable Long categoryId) {
        ProductDto productDto1 = productService.createProduct(productDto, categoryId);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);

    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    //Get All products
    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppConstant.PageNumber, required = false) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstant.PageSize, required = false) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = AppConstant.SortProductBy, required = false) String sortBy,
            @RequestParam(name = "sortOrder", defaultValue = AppConstant.SortOrder, required = false) String sortOrder


    ) {
        ProductResponse products = productService.getAllProducts(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(products, HttpStatus.OK);

    }

    //Get All products by categoryId
    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getAllProductsByCategory(@PathVariable Long categoryId) {
        ProductResponse allProductsByCategory = productService.getAllProductsByCategory(categoryId);
        return new ResponseEntity<>(allProductsByCategory, HttpStatus.OK);
    }

    //Get All products by keyword
    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getAllProductsByKeyword(@PathVariable String keyword) {
        ProductResponse allProductsByKeyword = productService.getAllProductsByKeyword(keyword);
        return new ResponseEntity<>(allProductsByKeyword, HttpStatus.OK);
    }

    @PutMapping(("/admin/products/{productId}"))
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Long productId) {
        ProductDto updatedProduct = productService.updateProduct(productDto, productId);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long productId) {
        ProductDto productDto = productService.deleteProduct(productId);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }
}
