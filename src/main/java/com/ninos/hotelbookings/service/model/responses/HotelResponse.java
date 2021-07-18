package com.ninos.hotelbookings.service.model.responses;

import com.ninos.hotelbookings.core.business.model.Hotel;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * models the response from the POST call to the /hotels endpoint
 */
public final class HotelResponse {

    @JsonProperty("id")
    @NotNull
    private long id;

    @JsonProperty("hotel_name")
    @NotEmpty
    private String hotelName;

    @JsonProperty("hotel_address")
    private String hotelAddress;

    @JsonProperty("star_rating")
    @NotNull
    @Min(0)
    @Max(7)
    private int starRating;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

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

    public HotelResponse() {
    }

    public HotelResponse(Hotel hotel) {
        id = hotel.getId();
        hotelName = hotel.getName();
        hotelAddress = hotel.getAddress() == null ? "N/A" : hotel.getAddress();
        starRating = hotel.getStarRating();
    }

    @Override
    public String toString() {
        return "HotelResponse{" +
                "id=" + id +
                ", hotelName='" + hotelName + '\'' +
                ", hotelAddress='" + hotelAddress + '\'' +
                ", starRating=" + starRating +
                '}';
    }
}
