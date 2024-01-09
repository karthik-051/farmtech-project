package com.mitron.onlinestore.validation.implementations;

import org.springframework.stereotype.Component;

import com.mitron.onlinestore.domain.entities.Product;
import com.mitron.onlinestore.domain.entities.Receipt;
import com.mitron.onlinestore.domain.models.service.CategoryServiceModel;
import com.mitron.onlinestore.domain.models.service.ProductServiceModel;
import com.mitron.onlinestore.domain.models.service.ReceiptServiceModel;
import com.mitron.onlinestore.validation.ProductValidationService;
import com.mitron.onlinestore.validation.ReceiptValidationService;

import java.util.List;

@Component
public class ReceiptValidationServiceImpl implements ReceiptValidationService {
    @Override
    public boolean isValid(Receipt receipt) {
        return receipt != null;
    }

    @Override
    public boolean isValid(ReceiptServiceModel receiptServiceModel) {
        return receiptServiceModel != null;
    }
}
