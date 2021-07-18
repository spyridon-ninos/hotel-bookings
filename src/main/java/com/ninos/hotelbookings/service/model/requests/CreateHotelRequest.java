package com.ninos.hotelbookings.service.model.requests;

import com.ninos.hotelbookings.core.business.model.Hotel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * models the request to create a hotel
 */
public final class CreateHotelRequest {

    @JsonProperty("hotel_name")
    @NotEmpty
    private String hotelName;

    @JsonProperty("hotel_address")
    private String hotelAddress;

    @JsonProperty("star_rating")
    @NotNull
    private int starRating;

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(final String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelAddress() {
        return hotelAddress;
    }

    public void setHotelAddress(final String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(final int starRating) {
        this.starRating = starRating;
    }

    public Hotel toModel() {
        Hotel hotel = new Hotel();
        hotel.setName(hotelName);
        hotel.setAddress(hotelAddress);
        hotel.setStarRating(starRating);

        return hotel;
    }

    @Override
    public String toString() {
        return "CreateHotelRequest{" +
                "hotelName='" + hotelName + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                ", starRating=" + starRating +
                '}';
    }
}
