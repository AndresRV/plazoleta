package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.Category;

public interface ICategoryServicePort {
    Category getCategoryByCategoryName(String categoryName);
}
