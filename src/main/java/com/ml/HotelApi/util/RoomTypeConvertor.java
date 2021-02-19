package com.ml.HotelApi.util;

import com.ml.HotelApi.exception.implementation.NotValidAmountException;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class RoomTypeConvertor {
    public static String getRoomTypeByPeopleAmount(int amount){
        if(amount==1) return "Single";
        if(amount==2) return "Double";
        if(amount==3) return "Triple";
        if(amount>=4) return "Múltiple";
        throw new NotValidAmountException(new Exception("Not registered type room for " + amount + " persons"));
    }
}
