package com.ml.HotelApi.stubs;

import com.ml.HotelApi.model.request.FullNewBookingDTO;

import static com.ml.HotelApi.stubs.NewBookingStub.getBookingStub;

public class FullBookingStub {
    public static FullNewBookingDTO getFullNewBookingStub(){
        FullNewBookingDTO stub = new FullNewBookingDTO();
        stub.setUserName("facetraro@gmail.com");
        stub.setBooking(getBookingStub());
        return stub;
    }
}
