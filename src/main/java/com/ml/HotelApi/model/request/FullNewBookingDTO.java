package com.ml.HotelApi.model.request;

public class FullNewBookingDTO {
    String userName;
    NewBookingDTO booking;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public NewBookingDTO getBooking() {
        return booking;
    }

    public void setBooking(NewBookingDTO booking) {
        this.booking = booking;
    }
}
