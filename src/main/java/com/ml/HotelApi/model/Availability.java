package com.ml.HotelApi.model;

import java.util.Date;

public class Availability {
    String code;
    Boolean available;
    Date availableSince, availableUntil;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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
}
