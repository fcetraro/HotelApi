package com.ml.HotelApi.service;

import com.ml.HotelApi.model.request.FullNewBookingDTO;
import com.ml.HotelApi.model.response.BookingResponseDTO;

public interface IBookingService {
    BookingResponseDTO book(FullNewBookingDTO booking);
}
