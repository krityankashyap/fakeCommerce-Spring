package org.example.fakecommerce.Controllers;

import org.example.fakecommerce.dtos.CreateCategoryDto;
import org.example.fakecommerce.schema.Category;
import org.example.fakecommerce.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService= categoryService;
    }

    @PostMapping
    public Category createCategory(@RequestBody CreateCategoryDto createCategoryDto){
        return this.categoryService.createCategory(createCategoryDto);
    }

    @GetMapping
    public List<Category> getAllCategory(){
        return this.categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id){
        return this.categoryService.getCategoryById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id){
         this.categoryService.deleteCategory(id);
    }


}
