package com.ml.HotelApi.filter.concret;

import com.ml.HotelApi.exception.implementation.DatesNotValidException;
import com.ml.HotelApi.filter.HotelFilter;
import com.ml.HotelApi.model.HotelDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

import static com.ml.HotelApi.util.DateFormat.dateFormat;

public class DateTo extends HotelFilter {
    Date dateTo;

    @Override
    public Predicate<HotelDTO> getPredicate() {
        return w -> w.getAvailableUntil().after(dateTo);
    }

    @Override
    public String getFilterName() {
        return "dateTo";
    }

    @Override
    public void setValue(String value){
        try {
            dateTo = new SimpleDateFormat(dateFormat).parse(value);
        } catch (ParseException e){
            throw new DatesNotValidException(e);
        }
    }
}
