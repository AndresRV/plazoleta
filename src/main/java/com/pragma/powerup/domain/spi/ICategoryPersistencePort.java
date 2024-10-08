package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.Category;

public interface ICategoryPersistencePort {
    Category getCategoryByCategoryName(String categoryName);
}
