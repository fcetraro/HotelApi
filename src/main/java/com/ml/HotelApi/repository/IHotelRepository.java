package com.ml.HotelApi.repository;

import com.ml.HotelApi.model.Availability;
import com.ml.HotelApi.model.HotelDTO;

import java.util.List;

public interface IHotelRepository {
    List<HotelDTO> getAllDTO();
    void add(Availability booking);
}
