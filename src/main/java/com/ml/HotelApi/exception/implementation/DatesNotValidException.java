package com.ml.HotelApi.exception.implementation;

public class DatesNotValidException extends RuntimeException {
    public DatesNotValidException(Throwable err) {
        super("Fechas no validas.", err);
    }
}
