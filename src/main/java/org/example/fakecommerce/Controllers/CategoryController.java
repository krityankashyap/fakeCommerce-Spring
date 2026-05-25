package org.example.fakecommerce.Controllers;

import org.example.fakecommerce.dtos.CreateCategoryDto;
import org.example.fakecommerce.exceptions.ResourceNotFoundException;
import org.example.fakecommerce.schema.Category;
import org.example.fakecommerce.services.CategoryService;
import org.example.fakecommerce.utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService= categoryService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Category>> createCategory(@RequestBody CreateCategoryDto createCategoryDto){
                Category category= this.categoryService.createCategory(createCategoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(category, "Category created successfully"));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Category>>> getAllCategory(){

        List<Category> categories=  this.categoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(categories, "Fetched all categories"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Category>> getCategoryById(@PathVariable Long id){

        Category category=  this.categoryService.getCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("resource with this id: "+ id + " not found"));

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(category, "Category with given if fetched"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteCategoryById(@PathVariable Long id){
          this.categoryService.deleteCategory(id);
         return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(null, "Category deleted successfully"));
    }


}
