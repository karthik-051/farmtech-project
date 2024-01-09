package com.mitron.onlinestore.validation;

import com.mitron.onlinestore.domain.entities.Receipt;
import com.mitron.onlinestore.domain.models.service.ReceiptServiceModel;

public interface ReceiptValidationService {
    boolean isValid(Receipt receipt);

    boolean isValid(ReceiptServiceModel receiptServiceModel);
}
