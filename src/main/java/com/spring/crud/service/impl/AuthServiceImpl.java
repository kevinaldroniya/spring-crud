package com.spring.crud.service.impl;

import com.spring.crud.dto.LoginUserRequest;
import com.spring.crud.dto.TokenResponse;
import com.spring.crud.entity.User;
import com.spring.crud.repository.UserRepository;
import com.spring.crud.service.AuthService;
import com.spring.crud.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ValidationService validationService;


    @Transactional
    @Override
    public TokenResponse login(LoginUserRequest request) {
        validationService.validate(request);

        User user = userRepository.findById(request.getUsername()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or Password wrong")
        );

        if (request.getPassword().equals(user.getPassword())){
            user.setToken(UUID.randomUUID().toString());
            user.setTokenExpiredAt(next5Mnts());
            userRepository.save(user);

            return TokenResponse.builder()
                    .token(user.getToken())
                    .expiredAt(user.getTokenExpiredAt())
                    .build();
        }else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Username Or Password Wrong");
        }
    }

    @Transactional
    @Override
    public void logout(User user) {
        user.setToken(null);
        user.setTokenExpiredAt(null);

        userRepository.save(user);
    }

    private Long next5Mnts() {
        return System.currentTimeMillis() + (1000 * 60 * 5);
    }


}
