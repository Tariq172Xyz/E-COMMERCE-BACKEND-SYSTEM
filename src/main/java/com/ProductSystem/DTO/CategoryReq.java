package com.ProductSystem.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryReq {

    @NotEmpty
    private String categoryName;
    @NotEmpty
    private String description;
}
