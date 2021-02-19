package com.ml.HotelApi.util;

import com.ml.HotelApi.model.response.StatusCode;
import org.springframework.http.HttpStatus;

public class StatusCodes {
    private final static String SUCCESSFUL_BOOKING_MESSAGE = "El proceso termino satisfactoriamente.";
    private final static String NO_HOTEL_FOR_BOOKING_MESSAGE = "No se encontro hotel para esa reserva.";
    public static StatusCode getOkBookingStatusCode(){
        StatusCode newStatusCode = new StatusCode();
        newStatusCode.setCode(HttpStatus.OK.value());
        newStatusCode.setMessage(SUCCESSFUL_BOOKING_MESSAGE);
        return newStatusCode;
    }
    public static StatusCode getHotelNotFoundForBookingStatusCode(){
        StatusCode newStatusCode = new StatusCode();
        newStatusCode.setCode(HttpStatus.NOT_FOUND.value());
        newStatusCode.setMessage(NO_HOTEL_FOR_BOOKING_MESSAGE);
        return newStatusCode;
    }
}
