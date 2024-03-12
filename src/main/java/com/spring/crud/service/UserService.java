package com.spring.crud.service;

import com.spring.crud.dto.RegisterUserRequest;
import com.spring.crud.dto.UpdateUserRequest;
import com.spring.crud.dto.UserResponse;
import com.spring.crud.entity.User;

public interface UserService {
    public void register(RegisterUserRequest request);

    public UserResponse getCurrentUser(User user);

    public UserResponse update(User user, UpdateUserRequest request);
}
