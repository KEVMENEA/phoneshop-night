package com.piseth.java.school.phoneshop_night.exception;

import com.piseth.java.school.phoneshop_night.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException{

    public ResourceNotFoundException(String resourceName, Long id) {
        super(HttpStatus.NOT_FOUND, String.format("%s with id = %d not found", resourceName, id));
    }
}
