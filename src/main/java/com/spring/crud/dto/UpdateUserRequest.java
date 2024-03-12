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
public class UpdateUserRequest {
    @Size(min = 5, message = "name lenght at least min 5")
    private String name;

    @Size(min = 5, message = "password lenght at least min 5")
    private String password;

}
