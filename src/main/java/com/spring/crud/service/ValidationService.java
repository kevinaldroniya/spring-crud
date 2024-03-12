package com.spring.crud.service;

import jakarta.validation.ConstraintViolation;

public interface ValidationService {
    public void validate(Object request);
}
