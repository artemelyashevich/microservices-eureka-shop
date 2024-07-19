package com.elyashevich.category_service.service;

import com.elyashevich.category_service.dao.CategoryDao;
import com.elyashevich.category_service.dto.CategoryDto;
import com.elyashevich.category_service.entity.CategoryEntity;
import com.elyashevich.category_service.exeption.NotFoundException;
import com.elyashevich.category_service.exeption.ServerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    @Override
    public CategoryEntity create(CategoryDto categoryDto) {
        return this.categoryDao.create(categoryDto)
                .orElseThrow(() -> new ServerException("Failed to create category"));
    }

    @Override
    public void update(String name, CategoryDto categoryDto) {
        this.getByName(name);
        this.categoryDao.update(name, categoryDto);
    }

    @Override
    public CategoryEntity getByName(String name) {
        return this.categoryDao.findByName(name)
                .orElseThrow(() -> new NotFoundException("No such category with name: %s".formatted(name)));
    }

    @Override
    public List<CategoryEntity> getAll() {
        return this.categoryDao.findAll();
    }

    @Override
    public void delete(String name) {
        this.getByName(name);
        this.categoryDao.delete(name);
    }
}
