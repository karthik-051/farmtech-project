package com.mitron.onlinestore.domain.models.service;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OrderProductServiceModel extends BaseServiceModel {

    private ProductServiceModel product;
    private BigDecimal price;

    public OrderProductServiceModel() {
    }

    @NotNull
    public ProductServiceModel getProduct() {
        return product;
    }

    public void setProduct(ProductServiceModel product) {
        this.product = product;
    }

    @NotNull
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
