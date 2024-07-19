package com.elyashevich.category_service.service;

import com.elyashevich.category_service.dto.CategoryDto;
import com.elyashevich.category_service.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {
    
    CategoryEntity create(CategoryDto categoryDto);
    
    void update(String name, CategoryDto categoryDto);
    
    CategoryEntity getByName(String name);
    
    List<CategoryEntity> getAll();

    void delete(String name);
}
