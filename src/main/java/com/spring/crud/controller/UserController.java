package com.spring.crud.controller;

import com.spring.crud.dto.RegisterUserRequest;
import com.spring.crud.dto.UpdateUserRequest;
import com.spring.crud.dto.UserResponse;
import com.spring.crud.dto.WebResponse;
import com.spring.crud.entity.User;
import com.spring.crud.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> register(@RequestBody RegisterUserRequest request){
        userService.register(request);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> getCurrentUser(User user){
        UserResponse userResponse = userService.getCurrentUser(user);
        return WebResponse.<UserResponse>builder().data(userResponse).build();
    }

    @PatchMapping(
            path = "/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<UserResponse> updateCurrentUser(User user,@RequestBody UpdateUserRequest request){
        UserResponse update = userService.update(user, request);
        return WebResponse.<UserResponse>builder().data(update).build();
    }
}
