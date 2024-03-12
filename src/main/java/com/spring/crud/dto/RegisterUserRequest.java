package com.spring.crud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserRequest {
    @NotBlank(message = "Username can't blank")
    @Size(max = 100, message = "username length max must 100 character")
    private String username;

    @NotBlank(message = "Password can't blank")
    @Size(max = 100, message = "password length max must 100 character")
    private String password;

    @NotBlank(message = "Name can't blank")
    @Size(max = 100, message = "name length max must 100 character")
    private String name;
}
