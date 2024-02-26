package com.ssafy.devoca.user.model;

import lombok.Data;

@Data
public class FavCategoryDTO {

    private Integer categoryId;
    private String categoryName;
    private Integer userChoiceYn;
}
