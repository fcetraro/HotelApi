package com.ml.HotelApi.model.request;

import com.ml.HotelApi.model.PeopleDTO;

import java.util.List;

public class PaymentMethodDTO {
    String type, number;
    int dues;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getDues() {
        return dues;
    }

    public void setDues(int dues) {
        this.dues = dues;
    }
}
