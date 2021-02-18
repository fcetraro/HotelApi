package com.ml.HotelApi.exception.implementation;

public class ProvinceNotFoundException extends RuntimeException {
    public ProvinceNotFoundException(String errorMessage, Throwable err) {
        super("Provincia ["+errorMessage +"] no encontrada.", err);
    }
}
