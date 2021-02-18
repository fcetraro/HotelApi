package com.ml.HotelApi.model;

import java.util.Date;

public class HotelDTO {
    String code, name, city, roomType;
    boolean booked;
    Date availableSince, availableUntil;
    int price;

    public HotelDTO(String code, String name, String city, String roomType, boolean booked, Date availableSince, Date availableUntil, int price) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.roomType = roomType;
        this.booked = booked;
        this.availableSince = availableSince;
        this.availableUntil = availableUntil;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Date getAvailableSince() {
        return availableSince;
    }

    public void setAvailableSince(Date availableSince) {
        this.availableSince = availableSince;
    }

    public Date getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(Date availableUntil) {
        this.availableUntil = availableUntil;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
