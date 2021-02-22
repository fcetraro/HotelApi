package com.ml.HotelApi.controller;

import com.ml.HotelApi.model.HotelDTO;
import com.ml.HotelApi.model.response.HotelResponseDTO;
import com.ml.HotelApi.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/hotels")
public class HotelController {
    private IHotelService service;
    @Autowired
    public HotelController(IHotelService service) {
        this.service = service;
    }
    @GetMapping("")
    public List<HotelResponseDTO> getHotels(@RequestParam(required = false) Map<String, String> queryMap){
        return service.get(queryMap);
    }
}
