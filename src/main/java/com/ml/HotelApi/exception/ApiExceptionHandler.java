package com.ml.HotelApi.exception;

import com.ml.HotelApi.exception.implementation.DatesNotValidException;
import com.ml.HotelApi.exception.implementation.ProvinceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {DatesNotValidException.class})
    public ResponseEntity<Object> handleApiRequestException(DatesNotValidException e){
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
}
