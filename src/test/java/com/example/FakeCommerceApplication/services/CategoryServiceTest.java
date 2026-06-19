package com.example.FakeCommerceApplication.services;

import org.example.fakecommerce.Repositories.CategoryRepository;
import org.example.fakecommerce.dtos.CreateCategoryDto;
import org.example.fakecommerce.schema.Category;
import org.example.fakecommerce.services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void createCategory_savesAndReturnCategory() {

        // arrange
        CreateCategoryDto createCategoryDto= CreateCategoryDto.builder().name("Test").build();
        Category testcategory= Category.builder().name("Test").build();
        testcategory.setId(1L);
        when(categoryRepository.save(any(Category.class))).thenReturn(testcategory);

        // act
        Category result= categoryService.createCategory(createCategoryDto);

        // assert
        assertEquals("Test" , result.name);
        assertEquals(1L, result.getId());
    }
}
