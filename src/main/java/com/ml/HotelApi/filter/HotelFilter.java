package com.ml.HotelApi.filter;

import com.ml.HotelApi.model.HotelDTO;

import java.util.function.Predicate;

public abstract class HotelFilter {
    public abstract Predicate<HotelDTO> getPredicate();
    public abstract String getFilterName();
    public boolean matchFilterName(String filterName){
        return getFilterName().equals(filterName);
    }

    public abstract void setValue(String value);
}
