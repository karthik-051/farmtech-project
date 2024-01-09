package com.mitron.onlinestore.validation;

import com.mitron.onlinestore.domain.models.service.UserServiceModel;

public interface UserValidationService {
    boolean isValid(UserServiceModel user);
}
