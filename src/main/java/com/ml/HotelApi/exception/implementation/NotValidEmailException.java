package com.ml.HotelApi.exception.implementation;

public class NotValidEmailException extends RuntimeException {
    public NotValidEmailException(Throwable err) {
        super("Email no valido.", err);
    }
}
