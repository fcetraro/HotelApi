package com.ml.HotelApi.filter.concret;

import com.ml.HotelApi.filter.HotelFilter;
import com.ml.HotelApi.model.HotelDTO;

import java.util.function.Predicate;

public class Code extends HotelFilter {
    String code;

    @Override
    public Predicate<HotelDTO> getPredicate() {
        return w -> w.getCode().equals(code);
    }

    @Override
    public String getFilterName() {
        return "code";
    }

    @Override
    public void setValue(String value){
        code = value;
    }
}
