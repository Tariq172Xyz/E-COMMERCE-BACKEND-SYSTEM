package com.ProductSystem.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResp {


    private Long categoryId;
    private String categoryName;
    private String description;
}
