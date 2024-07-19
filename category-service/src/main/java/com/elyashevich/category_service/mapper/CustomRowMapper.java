package com.elyashevich.category_service.mapper;


import com.elyashevich.category_service.entity.CategoryEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomRowMapper implements RowMapper<CategoryEntity> {

    @Override
    public CategoryEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        return  CategoryEntity
                .builder()
                .name(rs.getString("name"))
                .id(rs.getLong("id"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
