package com.spring.crud.service;

import com.spring.crud.dto.LoginUserRequest;
import com.spring.crud.dto.TokenResponse;
import com.spring.crud.entity.User;

public interface AuthService {

    public TokenResponse login(LoginUserRequest request);

    public void logout(User user);
}
