package com.piseth.java.school.phoneshop_night.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {
    // DTO for returning error JSON to the client.
    private  HttpStatus status;
    private  String message;
}
