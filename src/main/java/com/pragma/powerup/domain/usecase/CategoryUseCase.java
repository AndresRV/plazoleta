package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.ICategoryServicePort;
import com.pragma.powerup.domain.model.Category;
import com.pragma.powerup.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;

    @Override
    public Category getCategoryByCategoryName(String categoryName) {
        return categoryPersistencePort.getCategoryByCategoryName(categoryName);
    }
}
