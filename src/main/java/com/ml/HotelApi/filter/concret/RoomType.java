package com.ml.HotelApi.filter.concret;

import com.ml.HotelApi.filter.HotelFilter;
import com.ml.HotelApi.model.HotelDTO;

import java.util.function.Predicate;

public class RoomType extends HotelFilter {
    String roomType;

    @Override
    public Predicate<HotelDTO> getPredicate() {
        return w -> w.getRoomType().equals(roomType);
    }

    @Override
    public String getFilterName() {
        return "roomType";
    }

    @Override
    public void setValue(String value){
        roomType = value;
    }
}
