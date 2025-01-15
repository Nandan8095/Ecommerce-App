package com.ecommerce.service.impl;

import com.ecommerce.exception.ApiException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.ecommerce.payload.ProductDto;
import com.ecommerce.payload.ProductResponse;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductDto createProduct(ProductDto productDto, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "categoryId", categoryId)
        );
        Product product = modelMapper.map(productDto, Product.class);
        product.setCategory(category);
        product.setImage("demo.jpg");
        double specialPrice = product.getPrice() -
                ((product.getDiscount()*0.01) * product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);

        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                ?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);
        Page<Product> product1 = productRepository.findAll(pageDetails);


        List<Product> products = product1.getContent();

        if(products.isEmpty()){
            throw  new ApiException("Product table is Empty");
        }

        List<ProductDto> productDto = products.stream().map(product -> modelMapper.map(product, ProductDto.class)).toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductContent(productDto);
        productResponse.setPageNumber(product1.getNumber());
        productResponse.setPageSize(product1.getSize());
        productResponse.setTotalPages(product1.getTotalPages());
        productResponse.setTotalElements(product1.getTotalElements());
        productResponse.setLastPage(product1.isLast());
        return productResponse;
    }

    @Override
    public ProductResponse getAllProductsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "categoryId", categoryId)
        );

        List<Product> products = productRepository.findAllByCategory(category);

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductContent(productDtos);

        return productResponse;

    }

    @Override
    public ProductResponse getAllProductsByKeyword(String keyword) {

        List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%'+keyword+'%');

        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setProductContent(productDtos);

        return productResponse;

    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product", "productId", productId)
        );

        product.setProductName(productDto.getProductName());
        product.setDescription(productDto.getDescription());
        product.setQuantity(productDto.getQuantity());
        product.setPrice(productDto.getPrice());
        product.setDiscount(productDto.getDiscount());
        double specialPrice = productDto.getPrice() -
                ((productDto.getDiscount()*0.01) * productDto.getPrice());
        product.setSpecialPrice(specialPrice);

        Product savedProduct = productRepository.save(product);


        return modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product", "productId", productId)
        );
        productRepository.delete(product);
        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        return productDto;

    }
}
