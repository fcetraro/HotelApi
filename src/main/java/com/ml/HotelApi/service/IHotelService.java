package com.ml.HotelApi.service;

import com.ml.HotelApi.model.HotelDTO;

import java.util.List;
import java.util.Map;

public interface IHotelService {
    List<HotelDTO> get(Map<String,String> map);
}
