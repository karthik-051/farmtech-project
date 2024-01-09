package com.mitron.onlinestore.validation.implementations;

import org.springframework.stereotype.Component;

import com.mitron.onlinestore.domain.entities.Product;
import com.mitron.onlinestore.domain.models.service.CategoryServiceModel;
import com.mitron.onlinestore.domain.models.service.ProductServiceModel;
import com.mitron.onlinestore.validation.ProductValidationService;

import java.util.List;

@Component
public class ProductionValidationServiceImpl implements ProductValidationService {
    @Override
    public boolean isValid(Product product) {
        return product != null;
    }

    @Override
    public boolean isValid(ProductServiceModel product) {
        return product != null
                && areCategoriesValid(product.getCategories());
    }

    private boolean areCategoriesValid(List<CategoryServiceModel> categories) {
        return categories != null && !categories.isEmpty();
    }
}
