package com.ecommerce.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ApiException extends RuntimeException{


    public ApiException(String message) {
        super(message);
    }
}
