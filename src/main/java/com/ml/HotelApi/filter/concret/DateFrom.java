package com.ml.HotelApi.filter.concret;

import com.ml.HotelApi.exception.implementation.NotValidDateException;
import com.ml.HotelApi.filter.HotelFilter;
import com.ml.HotelApi.model.HotelDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

import static com.ml.HotelApi.util.DateFormat.dateFormat;

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
            dateFrom = new SimpleDateFormat(dateFormat).parse(value);
        } catch (ParseException e){
            throw new NotValidDateException(e);
        }
    }
}
