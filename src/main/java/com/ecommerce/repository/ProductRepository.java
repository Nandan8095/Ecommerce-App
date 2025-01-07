package com.ecommerce.repository;

import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByCategory(Category category);

    List<Product> findByProductNameLikeIgnoreCase(String keyword);
}
