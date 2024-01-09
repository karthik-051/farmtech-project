package com.mitron.onlinestore.service;


import java.util.List;

import com.mitron.onlinestore.domain.models.service.UserServiceModel;

public interface UserService {

    UserServiceModel register(UserServiceModel userServiceModel);

    List<UserServiceModel> findAllUsers();

    UserServiceModel findByUsername(String username);

    UserServiceModel findById(String id);

    void updateRole(String id, String role);

    UserServiceModel findUserByUserName(String name);
}
