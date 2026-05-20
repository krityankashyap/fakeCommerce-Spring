package org.example.fakecommerce.services;

import org.example.fakecommerce.Repositories.CategoryRepository;
import org.example.fakecommerce.dtos.CreateCategoryDto;
import org.example.fakecommerce.schema.Category;

import java.util.List;

public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository= categoryRepository;
    }

    public Category createCategory(CreateCategoryDto requestdto){
        Category newCategory= Category.builder()
                .name(requestdto.getName())
                .build();

        return this.categoryRepository.save(newCategory);
    }

    public List<Category> getAllCategory(){
        return this.categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return this.categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void deleteCategory(Long id){
         this.categoryRepository.deleteById(id);
    }
}
