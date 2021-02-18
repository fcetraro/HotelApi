package com.ml.HotelApi.repository;

import com.ml.HotelApi.model.HotelDTO;

import java.util.List;

public interface IHotelRepository {
    List<HotelDTO> getAll();
}
