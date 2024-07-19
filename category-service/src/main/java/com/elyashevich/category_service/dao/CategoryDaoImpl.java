package com.elyashevich.category_service.dao;

import com.elyashevich.category_service.dto.CategoryDto;
import com.elyashevich.category_service.entity.CategoryEntity;
import com.elyashevich.category_service.mapper.CustomRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao {

    private final NamedParameterJdbcTemplate template;

    @Override
    public List<CategoryEntity> findAll() {
        final String sql = "SELECT * FROM `categories`";
        return this.template.query(sql, new CustomRowMapper());
    }

    @Override
    public Optional<CategoryEntity> findByName(final String name) {
        final String sql = "SELECT * FROM `categories` WHERE name = :name";
        final SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        return Optional.ofNullable(this.template.queryForObject(
                        sql, parameterSource, new CustomRowMapper()
                )
        );
    }

    @Override
    public Optional<CategoryEntity> create(final CategoryDto categoryDto) {
        final String sql = "INSERT INTO `categories` (name) "
                + "VALUES (:name)";
        final SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", categoryDto.name());
        return Optional.ofNullable(
                this.template.queryForObject(sql, parameterSource, new CustomRowMapper()
                )
        );
    }

    @Override
    public void update(String name, final CategoryDto categoryDto) {
        final String sql = "UPDATE `categories` SET name = :name WHERE name = :oldName";
        final SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("name", categoryDto.name())
                .addValue("oldName", name);
        this.template.update(sql, parameterSource);
    }

    @Override
    public void delete(final String name) {
        final String sql = "DELETE FROM `categories` WHERE name = :name";
        final SqlParameterSource parameterSource = new MapSqlParameterSource("name", name);
        this.template.update(sql, parameterSource);
    }
}
