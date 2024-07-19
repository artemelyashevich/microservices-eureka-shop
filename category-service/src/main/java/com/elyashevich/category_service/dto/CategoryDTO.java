package com.elyashevich.category_service.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CategoryDto(
        @NotNull(message = "Name can not be empty")
        @Length(min = 1, message = "Min length of name = 1")
        String name
) {
}
