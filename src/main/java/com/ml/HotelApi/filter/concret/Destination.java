package com.ml.HotelApi.filter.concret;

import com.ml.HotelApi.filter.HotelFilter;
import com.ml.HotelApi.model.HotelDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;

public class Destination extends HotelFilter {
    String destination;

    @Override
    public Predicate<HotelDTO> getPredicate() {
        return w -> w.getCity().equals(destination);
    }

    @Override
    public String getFilterName() {
        return "destination";
    }

    @Override
    public void setValue(String value){
        destination = value;
    }
}
