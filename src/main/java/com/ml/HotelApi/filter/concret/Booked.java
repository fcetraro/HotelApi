package com.ml.HotelApi.filter.concret;

import com.ml.HotelApi.filter.HotelFilter;
import com.ml.HotelApi.model.HotelDTO;

import java.util.function.Predicate;

public class Booked extends HotelFilter {
    Boolean booked;

    @Override
    public Predicate<HotelDTO> getPredicate() {
        return w -> booked.equals(w.isBooked());
    }

    @Override
    public String getFilterName() {
        return "booked";
    }

    @Override
    public void setValue(String value){
        booked = Boolean.parseBoolean(value);
    }
}
