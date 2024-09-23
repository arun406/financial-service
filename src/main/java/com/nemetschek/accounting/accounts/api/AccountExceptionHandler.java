package com.nemetschek.accounting.accounts.api;

import com.nemetschek.accounting.accounts.exception.AccountNotFoundException;
import com.nemetschek.accounting.accounts.exception.InvalidInputException;
import com.nemetschek.util.ApiResponse;
import com.nemetschek.util.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AccountExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleAccountNotFoundException(AccountNotFoundException e) {
        return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), ResponseStatus.F), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidInputException(InvalidInputException e) {
        return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), ResponseStatus.F), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(new ApiResponse<>(errors, ResponseStatus.F), HttpStatus.BAD_REQUEST);
    }
}
