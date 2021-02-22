package com.ml.HotelApi.stubs;

import com.ml.HotelApi.model.BookingDTO;
import com.ml.HotelApi.model.PeopleDTO;
import com.ml.HotelApi.model.request.NewBookingDTO;

import java.util.ArrayList;
import java.util.List;

import static com.ml.HotelApi.stubs.PaymentMethodStub.getCreditPaymentMethod;
import static com.ml.HotelApi.stubs.PeopleStub.getPeople;

public class NewBookingStub {
    public static NewBookingDTO getBookingStub(){
        List<PeopleDTO> people = new ArrayList<>();
        people.add(getPeople());
        people.add(getPeople());
        NewBookingDTO booking = new NewBookingDTO();
        booking.setDateFrom("12/02/2021");
        booking.setDateTo("17/02/2021");
        booking.setDestination("Buenos Aires");
        booking.setHotelCode("BH-0002");
        booking.setPeople(people);
        booking.setRoomType("DOUBLE");
        booking.setPeopleAmount(2);
        booking.setPaymentMethod(getCreditPaymentMethod());
        return booking;
    }
}
