package com.ml.HotelApi.filter.concret;

import com.ml.HotelApi.exception.implementation.DatesNotValidException;
import com.ml.HotelApi.filter.HotelFilter;
import com.ml.HotelApi.model.HotelDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

public class DateFrom extends HotelFilter {
    Date dateFrom;

    @Override
    public Predicate<HotelDTO> getPredicate() {
        return w -> w.getAvailableSince().before(dateFrom);
    }

    @Override
    public String getFilterName() {
        return "dateFrom";
    }

    @Override
    public void setValue(String value){
        try {
            dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(value);
        } catch (ParseException e){
            throw new DatesNotValidException(e);
        }
    }
}
