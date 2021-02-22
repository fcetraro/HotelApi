package com.ml.HotelApi.service.implementation;

import com.ml.HotelApi.filter.HotelFilter;
import com.ml.HotelApi.filter.concret.*;
import com.ml.HotelApi.model.BookingDTO;
import com.ml.HotelApi.model.HotelDTO;
import com.ml.HotelApi.model.request.FullNewBookingDTO;
import com.ml.HotelApi.model.request.NewBookingDTO;
import com.ml.HotelApi.model.response.BookingResponseDTO;
import com.ml.HotelApi.service.IBookingService;
import com.ml.HotelApi.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.ml.HotelApi.util.DateFormat.getDaysBetween;
import static com.ml.HotelApi.util.StatusCodes.getHotelNotFoundForBookingStatusCode;
import static com.ml.HotelApi.util.StatusCodes.getOkBookingStatusCode;
import static com.ml.HotelApi.util.Validator.*;

@Service
public class BookingService implements IBookingService {
    @Autowired
    private IHotelService hotelService;
    @Autowired
    public BookingService(IHotelService hotelService) {
        this.hotelService = hotelService;
    }
    private Map<String, String> getFiltersForBooking(NewBookingDTO booking){
        Map<String, String> filters = new HashMap<>();
        HotelFilter roomTypeFilter = new RoomType();
        filters.put(roomTypeFilter.getFilterName(),booking.getRoomType().toUpperCase(Locale.ROOT));
        HotelFilter destinationFilter = new Destination();
        filters.put(destinationFilter.getFilterName(),booking.getDestination());
        HotelFilter codeFilter = new Code();
        filters.put(codeFilter.getFilterName(),booking.getHotelCode());
        HotelFilter dateFrom = new DateFrom();
        filters.put(dateFrom.getFilterName(),booking.getDateFrom());
        HotelFilter dateTo = new DateTo();
        filters.put(dateTo.getFilterName(),booking.getDateTo());
        HotelFilter booked = new Booked();
        filters.put(booked.getFilterName(),"false");
        return filters;
    }
    @Override
    public BookingResponseDTO book(FullNewBookingDTO booking) {
        verifyBooking(booking);
        return makeBooking(booking);
    }

    private BookingResponseDTO makeBooking(FullNewBookingDTO booking) {
        BookingResponseDTO bookingResponse = new BookingResponseDTO();
        Map<String, String> filters = getFiltersForBooking(booking.getBooking());
        List<HotelDTO> hotelsFit = hotelService.getDTOS(filters);
        HotelFilter booked = new Booked();
        filters = getFiltersForBooking(booking.getBooking());
        filters.replace(booked.getFilterName(),"true");
        List<HotelDTO> bookedHotel = hotelService.getDTOS(filters);
        if(hotelsFit.size()!=0 && bookedHotel.size() <= 0){
            HotelDTO bookingHotel = hotelsFit.get(0);
            bookingResponse = setValues(booking, bookingResponse, bookingHotel);
            hotelService.modifyAvailability(getFiltersForBooking(booking.getBooking())
                    ,getFiltersForBooking(booking.getBooking()));
            bookingResponse.setStatusCode(getOkBookingStatusCode());
        } else {
            bookingResponse.setStatusCode(getHotelNotFoundForBookingStatusCode());
        }
        return bookingResponse;
    }

    private BookingResponseDTO setValues(FullNewBookingDTO booking, BookingResponseDTO bookingResponse, HotelDTO bookingHotel) {
        double daysBetween = getDaysBetween(booking.getBooking().getDateFrom(), booking.getBooking().getDateTo());
        double amountWithoutInterest = bookingHotel.getPrice() * daysBetween;
        double interest = getInterest(booking.getBooking().getPaymentMethod().getDues());
        bookingResponse.setAmount(amountWithoutInterest);
        bookingResponse.setInterest(interest);
        bookingResponse.setTotal(amountWithoutInterest + (amountWithoutInterest*interest/100));
        bookingResponse.setUserName(booking.getUserName());
        bookingResponse.setBooking(getBookingDTO(booking.getBooking()));
        return bookingResponse;
    }

    private BookingDTO getBookingDTO(NewBookingDTO newBooking) {
        BookingDTO booking = new BookingDTO();
        booking.setDateFrom(newBooking.getDateFrom());
        booking.setDateTo(newBooking.getDateTo());
        booking.setDestination(newBooking.getDestination());
        booking.setHotelCode(newBooking.getHotelCode());
        booking.setPeople(newBooking.getPeople());
        booking.setRoomType(newBooking.getRoomType());
        booking.setPeopleAmount(newBooking.getPeopleAmount());
        return booking;
    }

    private double getInterest(int dues) {
        int interest = 1;
        int firstInterest = 5;
        if(dues>1){
            interest = firstInterest * ((dues/3)+1);
        }
        return interest;
    }

    private void verifyBooking(FullNewBookingDTO booking) {
        validateDatesString(booking.getBooking().getDateFrom(),booking.getBooking().getDateTo());
        validateDestination(booking.getBooking().getDestination(), hotelService.getDTOS(new HashMap<>()));
        validateEmail(booking.getUserName());
        validatePeopleRoomType(booking.getBooking().getPeopleAmount(), booking.getBooking().getRoomType());
        validatePaymentMethod(booking.getBooking().getPaymentMethod());
    }
}
