package com.ml.HotelApi.exception;

import com.ml.HotelApi.exception.implementation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {NotValidDateException.class})
    public ResponseEntity<Object> handleApiRequestException(NotValidDateException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(e.getMessage(), e, status, ZonedDateTime.now());
        return new ResponseEntity<>(exception, status);
    }
    @ExceptionHandler(value = {ProvinceNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(ProvinceNotFoundException e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiException exception = new ApiException(e.getMessage(), e, status, ZonedDateTime.now());
        return new ResponseEntity<>(exception, status);
    }
    @ExceptionHandler(value = {NotValidEmailException.class})
    public ResponseEntity<Object> handleApiRequestException(NotValidEmailException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(e.getMessage(), e, status, ZonedDateTime.now());
        return new ResponseEntity<>(exception, status);
    }
    @ExceptionHandler(value = {NotValidAmountException.class})
    public ResponseEntity<Object> handleApiRequestException(NotValidAmountException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(e.getMessage(), e, status, ZonedDateTime.now());
        return new ResponseEntity<>(exception, status);
    }
    @ExceptionHandler(value = {NotValidPaymentMethodException.class})
    public ResponseEntity<Object> handleApiRequestException(NotValidPaymentMethodException e){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(e.getMessage(), e, status, ZonedDateTime.now());
        return new ResponseEntity<>(exception, status);
    }
    @ExceptionHandler(value = {FilterNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(FilterNotFoundException e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiException exception = new ApiException(e.getMessage(), e, status, ZonedDateTime.now());
        return new ResponseEntity<>(exception, status);
    }
}
