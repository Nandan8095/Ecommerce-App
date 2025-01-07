package com.ecommerce.controller;



import com.ecommerce.configure.AppConstant;
import com.ecommerce.payload.CategoryDto;
import com.ecommerce.payload.CategoryResponse;
import com.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("/admin/category")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);

    }

    @GetMapping("/public/category")
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam (name="pageNumber",defaultValue = AppConstant.PageNumber,required = false)Integer pageNumber,
            @RequestParam (name="pageSize",defaultValue = AppConstant.PageSize,required = false)Integer pageSize,
            @RequestParam (name="sortBy",defaultValue = AppConstant.SortCategoryBy,required = false)String sortBy,
            @RequestParam (name="sortOrder",defaultValue = AppConstant.SortOrder,required = false)String sortOrder) {
        CategoryResponse allCategories = categoryService.getAllCategories(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(allCategories, HttpStatus.OK);
    }

    @GetMapping("/public/category/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        CategoryDto categoryById = categoryService.getCategoryById(id);
        return new ResponseEntity<>(categoryById, HttpStatus.OK);
    }

    @PutMapping("/admin/updateCategory/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable Long id) {
        CategoryDto categoryDto1 = categoryService.updateCategory(categoryDto, id);
        return new ResponseEntity<>(categoryDto1, HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteCategory/{id}")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Long id) {
        CategoryDto categoryDto = categoryService.deleteCategory(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

}
