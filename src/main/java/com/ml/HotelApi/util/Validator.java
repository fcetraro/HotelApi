package com.ml.HotelApi.util;

import com.ml.HotelApi.exception.implementation.DatesNotValidException;
import com.ml.HotelApi.exception.implementation.ProvinceNotFoundException;
import com.ml.HotelApi.model.HotelDTO;

import java.util.Date;
import java.util.List;

public class Validator {
    private static boolean isDestinationValid(String destination, List<HotelDTO> hotels){
        for (HotelDTO hotel:hotels) {
            if(hotel.getCity().equals(destination)) return true;
        }
        return false;
    }
    public static void validateDestination(String destination, List<HotelDTO> hotels){
        if(!isDestinationValid(destination, hotels)){
            throw new ProvinceNotFoundException(destination,new Exception("Province not found"));
        }
    }
    public static void validateRangeDates(Date dateFrom, Date dateTo) {
        if(dateFrom.after(dateTo)){
            throw new DatesNotValidException(new Exception());
        }
    }
}
