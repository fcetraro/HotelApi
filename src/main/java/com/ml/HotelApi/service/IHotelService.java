package com.ml.HotelApi.service;

import com.ml.HotelApi.model.HotelDTO;
import com.ml.HotelApi.model.response.HotelResponseDTO;

import java.util.List;
import java.util.Map;

public interface IHotelService {
    List<HotelResponseDTO> get(Map<String,String> map);
    List<HotelDTO> getDTOS(Map<String,String> map);
    void modifyAvailability(Map<String,String> filter, Map<String,String> map);
}
