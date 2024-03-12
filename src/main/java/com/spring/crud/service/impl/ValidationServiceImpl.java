package com.spring.crud.service.impl;

import com.spring.crud.service.ValidationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Autowired
    private Validator validator;

    @Override
    public void validate(Object request) {
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(request);
        if (constraintViolationSet.size()!=0){
            throw new ConstraintViolationException(constraintViolationSet);
        }
    }
}
