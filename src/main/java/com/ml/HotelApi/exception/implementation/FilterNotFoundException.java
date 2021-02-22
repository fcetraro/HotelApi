package com.ml.HotelApi.exception.implementation;

public class FilterNotFoundException extends RuntimeException {
    public FilterNotFoundException(String message, Throwable err) {
        super(message, err);
    }
}
