package com.elyashevich.category_service.dao;

import com.elyashevich.category_service.dto.CategoryDto;
import com.elyashevich.category_service.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {

    List<CategoryEntity> findAll();

    Optional<CategoryEntity> findByName(String name);

    Optional<CategoryEntity> create(CategoryDto categoryDto);

    void update(String name, CategoryDto categoryDto);

    void delete(String name);
}
