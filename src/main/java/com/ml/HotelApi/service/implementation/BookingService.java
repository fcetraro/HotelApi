package com.ml.HotelApi.service.implementation;

import com.ml.HotelApi.exception.implementation.NotValidAmountException;
import com.ml.HotelApi.model.request.FullNewBookingDTO;
import com.ml.HotelApi.model.response.BookingResponseDTO;
import com.ml.HotelApi.repository.IHotelRepository;
import com.ml.HotelApi.service.IBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.ml.HotelApi.util.RoomTypeConvertor.getRoomTypeByPeopleAmount;
import static com.ml.HotelApi.util.Validator.*;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private IHotelRepository hotels;
    @Autowired
    public BookingService(IHotelRepository hotelRepository) {
        this.hotels = hotelRepository;
    }
    @Override
    public BookingResponseDTO book(FullNewBookingDTO booking) {
        verifyBooking(booking);
        return null;
    }

    private void verifyBooking(FullNewBookingDTO booking) {
        validateDatesString(booking.getBooking().getDateFrom(),booking.getBooking().getDateTo());
        validateDestination(booking.getBooking().getDestination(), hotels.getAll());
        validateEmail(booking.getUserName());
        validatePeopleRoomType(booking.getBooking().getPeopleAmount(), booking.getBooking().getRoomType());
        validatePaymentMethod(booking.getBooking().getPaymentMethod());
    }
}
