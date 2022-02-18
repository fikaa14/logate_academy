package com.academy.demo.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;

@Getter
@RequiredArgsConstructor
public class ValidationException extends Exception{

    private final String message;
    private final Errors errors;

}
