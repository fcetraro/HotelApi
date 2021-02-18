package com.ml.HotelApi.controller;

import com.ml.HotelApi.model.request.FullNewBookingDTO;
import com.ml.HotelApi.model.response.BookingResponseDTO;
import com.ml.HotelApi.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingController {
    private IBookingService service;
    @Autowired
    public BookingController(IBookingService service) {
        this.service = service;
    }
    @PostMapping("")
    public BookingResponseDTO booking(@RequestBody FullNewBookingDTO booking){
        return service.book(booking);
    }
}
