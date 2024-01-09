package org.softuni.onlinegrocery.unit.validation;//package org.softuni.productshop.unit.validation;

import org.junit.Before;
import org.junit.Test;

import com.mitron.onlinestore.domain.entities.Category;
import com.mitron.onlinestore.domain.entities.Product;
import com.mitron.onlinestore.domain.models.service.CategoryServiceModel;
import com.mitron.onlinestore.domain.models.service.ProductServiceModel;
import com.mitron.onlinestore.service.ProductService;
import com.mitron.onlinestore.validation.ProductValidationService;
import com.mitron.onlinestore.validation.implementations.ProductionValidationServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProductValidationServiceTests {
    private ProductValidationService service;

    @Before
    public void setupTest() {
        service = new ProductionValidationServiceImpl();
    }

    @Test
    public void isValidWithProduct_whenValid_true() {
        Product product = new Product();
        product.setCategories(List.of(new Category()));
        boolean result = service.isValid(product);
        assertTrue(result);
    }

    @Test
    public void isValidWithProduct_whenNull_false() {
        Product product = null;
        boolean result = service.isValid(product);
        assertFalse(result);
    }

    @Test
    public void isValidWithProductServiceModel_whenNull_false() {
        ProductServiceModel product = null;
        boolean result = service.isValid(product);
        assertFalse(result);
    }

    @Test
    public void isValidWithProductServiceModel_whenValid_true() {
        ProductServiceModel product = new ProductServiceModel();
        product.setCategories(List.of(new CategoryServiceModel()));
        boolean result = service.isValid(product);
        assertTrue(result);
    }

    @Test
    public void t1() {
        ProductServiceModel product = new ProductServiceModel();
        product.setCategories(null);

        boolean result = service.isValid(product);
        assertFalse(result);
    }


    @Test
    public void t2() {
        ProductServiceModel product = new ProductServiceModel();
        product.setCategories(new ArrayList<>());

        boolean result = service.isValid(product);
        assertFalse(result);
    }
}
