package com.ml.HotelApi.util;

import com.ml.HotelApi.exception.implementation.NotValidDateException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    public final static String DATE_FORMAT = "dd/MM/yyyy";

    public static double getDaysBetween(String dateFromString, String dateToString) {
        try{
            Date dateFrom = new SimpleDateFormat(DATE_FORMAT).parse(dateFromString);
            Date dateTo = new SimpleDateFormat(DATE_FORMAT).parse(dateToString);
            long difference = dateTo.getTime() - dateFrom.getTime();
            return difference/(1000*60*60*24);
        } catch (ParseException e){
            throw new NotValidDateException(new Exception("Not valid format"));
        }
    }
}
