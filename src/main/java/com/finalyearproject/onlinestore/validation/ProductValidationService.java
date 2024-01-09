package com.mitron.onlinestore.validation;

import com.mitron.onlinestore.domain.entities.Product;
import com.mitron.onlinestore.domain.models.service.ProductServiceModel;

public interface ProductValidationService {
    boolean isValid(Product product);

    boolean isValid(ProductServiceModel product);
}
