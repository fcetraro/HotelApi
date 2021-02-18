package com.ml.HotelApi.model.response;

import com.ml.HotelApi.model.request.NewBookingDTO;

public class BookingResponseDTO {
    String userName;
    double amount, interest, total;
    NewBookingDTO booking;
    StatusCode statusCode;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public NewBookingDTO getBooking() {
        return booking;
    }

    public void setBooking(NewBookingDTO booking) {
        this.booking = booking;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
