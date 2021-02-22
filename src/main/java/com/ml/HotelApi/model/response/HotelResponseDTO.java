package com.ml.HotelApi.model.response;

import java.util.Date;

public class HotelResponseDTO {
    String code, name, city, roomType;
    int price;

    public HotelResponseDTO(String code, String name, String city, String roomType, int price) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.roomType = roomType;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
