package com.mitron.onlinestore.validation.implementations;

import org.springframework.stereotype.Component;

import com.mitron.onlinestore.domain.models.service.UserServiceModel;
import com.mitron.onlinestore.validation.UserValidationService;

@Component
public class UserValidationServiceImpl implements UserValidationService {
    @Override
    public boolean isValid(UserServiceModel user) {
        return user != null;
    }
}
