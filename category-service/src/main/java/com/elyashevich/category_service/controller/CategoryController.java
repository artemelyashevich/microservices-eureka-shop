package com.elyashevich.category_service.controller;

import com.elyashevich.category_service.dto.CategoryDto;
import com.elyashevich.category_service.entity.CategoryEntity;
import com.elyashevich.category_service.service.CategoryService;
import com.elyashevich.category_service.utils.Utils;
import jakarta.validation.Valid;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@RestController("categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getAll() {
        return ResponseEntity
                .ok()
                .body(this.categoryService.getAll());
    }

    @PostMapping
    public ResponseEntity<CategoryEntity> create(
            final @Valid @RequestBody CategoryDto dto,
            final BindingResult bindingResult,
            final UriComponentsBuilder uriComponentsBuilder
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        final CategoryEntity category = this.categoryService.create(dto);
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .replacePath("/api/v1/categories/{categoryName}")
                                .build(Map.of("categoryName", category.getName()))
                )
                .body(category);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryEntity> getByName(final @PathVariable("name") String name) {
        return ResponseEntity
                .ok()
                .body(this.categoryService.getByName(name));
    }

    @PutMapping("{name}")
    public ResponseEntity<Void> update(
            final @PathVariable("name") String name,
            final @Valid @RequestBody CategoryDto dto,
            final BindingResult bindingResult
    ) throws BindException {
        Utils.validateBindingResult(bindingResult);
        this.categoryService.update(name, dto);
        return ResponseEntity
                .accepted()
                .build();
    }

    @DeleteMapping("{name}")
    public ResponseEntity<Void> delete(final @PathVariable("name") String name) {
        this.categoryService.delete(name);
        return ResponseEntity
                .noContent()
                .build();
    }
}
