package com.ml.HotelApi.util;

import com.ml.HotelApi.exception.implementation.*;
import com.ml.HotelApi.model.HotelDTO;
import com.ml.HotelApi.model.request.PaymentMethodDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.ml.HotelApi.util.DateFormat.DATE_FORMAT;
import static com.ml.HotelApi.util.RoomTypeConvertor.getRoomTypeByPeopleAmount;

public class Validator {
    private static final String emailRegex = "^(.+)@(.+)$";
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
    private static void validateRangeDates(Date dateFrom, Date dateTo) {
        if(dateFrom.after(dateTo)){
            throw new NotValidDateException(new Exception());
        }
    }
    public static void validateDatesString(String dateFromString, String dateToString) {
        try{
            Date dateFrom = new SimpleDateFormat(DATE_FORMAT).parse(dateFromString);
            Date dateTo = new SimpleDateFormat(DATE_FORMAT).parse(dateToString);
            validateRangeDates(dateFrom, dateTo);
        } catch (ParseException e) {
            throw new NotValidDateException(e);
        }
    }
    public static void validateEmail(String email){
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()) throw new NotValidEmailException(new Exception(email + " is not a valid!"));
    }
    public static void validatePeopleRoomType(int peopleAmount, String roomType) {
        String shouldBeRoomType = getRoomTypeByPeopleAmount(peopleAmount).toUpperCase(Locale.ROOT);
        if(!roomType.equals(shouldBeRoomType)) {
            throw new NotValidAmountException(new Exception(roomType+" is not valid for "+peopleAmount+" persons"));
        }
    }
    private static void validateCreditCard(PaymentMethodDTO method){
        if(method.getDues()<=0) {
            throw new NotValidPaymentMethodException(new Exception("Se ha ingresado una cantidad de " +
                    "cuotas igual o menor a 0."));
        }
    }
    private static void validateDebitCard(PaymentMethodDTO method){
        if(method.getDues()!=1) {
            throw new NotValidPaymentMethodException(new Exception("Se ha ingresado una cantidad de" +
                    " cuotas diferente a 1."));
        }
    }
    public static void validatePaymentMethod(PaymentMethodDTO method){
        switch (method.getType().toUpperCase(Locale.ROOT))
        {
            case "CREDIT":  validateCreditCard(method);
                break;
            case "DEBIT":  validateDebitCard(method);
                break;
            default:
                throw new NotValidPaymentMethodException(new Exception(method.getType() + " is not a valid " +
                        "payment method"));
        }
    }
}
