package com.nemetschek.accounting.transactions.api;

import com.nemetschek.accounting.transactions.exception.InvalidTransactionException;
import com.nemetschek.util.ApiResponse;
import com.nemetschek.util.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TransactionExceptionHandler {

    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<ApiResponse<String>> handleInvalidTransactionException(InvalidTransactionException e) {
        return new ResponseEntity<>(new ApiResponse<>(e.getMessage(), ResponseStatus.F), HttpStatus.BAD_REQUEST);
    }
}
