package com.ninos.hotelbookings.service.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public final class UpdateHotelRequest {

    @JsonProperty("hotel_address")
    private String hotelAddress;

    @JsonProperty("star_rating")
    @Min(0)
    @Max(7)
    private Integer starRating;

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(final String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public Integer getStarRating() {
        return starRating;
    }

    public void setStarRating(final Integer starRating) {
        this.starRating = starRating;
    }

    @Override
    public String toString() {
        return "UpdateHotelRequest{" +
                "hotelAddress='" + hotelAddress + '\'' +
                ", starRating=" + starRating +
                '}';
    }
}
