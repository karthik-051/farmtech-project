package com.mitron.onlinestore.domain.models.binding;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import com.mitron.onlinestore.domain.entities.Category;
import com.mitron.onlinestore.util.constants.ValidationErrorMessages;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.mitron.onlinestore.util.constants.AppConstants.*;
import static com.mitron.onlinestore.util.constants.ValidationErrorMessages.*;

import java.math.BigDecimal;
import java.util.List;

public class ProductEditBindingModel {

    private String name;
    private String description;
    private BigDecimal price;
    private MultipartFile image;
    private String imageUrl;
    private List<Category> categories;

    public ProductEditBindingModel() {
    }


    @NotNull(message = PRODUCT_NAME_EMPTY_FIELD_ERROR_MSG)
    @Size(min = PRODUCT_NAME_MIN_LENGTH, max = PRODUCT_NAME_MAX_LENGTH, message = PRODUCT_NAME_LENGTH)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = PRODUCT_DESCRIPTION_EMPTY_FIELD_ERROR_MSG)
    @NotEmpty(message = PRODUCT_DESCRIPTION_EMPTY_FIELD_ERROR_MSG)
    @Length(max = PRODUCT_DESCRIPTION_MAX_LENGTH, message = PRODUCT_DESCRIPTION_MAX_LENGTH_ERROR_MSG)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull(message = PRODUCT_PRICE_EMPTY_FIELD_ERROR_MSG)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NotEmpty(message = PRODUCT_CATEGORIES_EMPTY_FIELD_ERROR_MSG)
    public List<Category> getCategories() {
        return this.categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
