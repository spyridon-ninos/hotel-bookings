package com.ninos.hotelbookings.service.model.responses;

import com.ninos.hotelbookings.core.business.model.Booking;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class BookingResponse {

    @JsonProperty("id")
    @NotNull
    @Min(0)
    private long id;

    @JsonProperty("customer_name")
    @NotEmpty
    @NotBlank
    private String customerName;

    @JsonProperty("customer_surname")
    @NotEmpty
    @NotBlank
    private String customerSurname;

    @JsonProperty("num_of_pax")
    @NotNull
    private int numOfPax;

    @JsonProperty("hotel_id")
    @NotNull
    private long hotelId;

    @JsonProperty("price")
    @NotNull
    private int price;

    @JsonProperty("currency")
    @NotEmpty
    @NotBlank
    private String currency;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(final String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerSurname() {
        return customerSurname;
    }

    public void setCustomerSurname(final String customerSurname) {
        this.customerSurname = customerSurname;
    }

    public int getNumOfPax() {
        return numOfPax;
    }

    public void setNumOfPax(final int numOfPax) {
        this.numOfPax = numOfPax;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(final long hotelId) {
        this.hotelId = hotelId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(final int price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public BookingResponse() {
    }

    public BookingResponse(Booking booking) {
        id = booking.getId();
        customerName = booking.getCustomerName();
        customerSurname = booking.getCustomerSurname();
        numOfPax = booking.getNumOfPax();
        hotelId = booking.getHotelId();
        price = booking.getPrice();
        currency = booking.getCurrency();
    }

    @Override
    public String toString() {
        return "BookingResponse{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", numOfPax=" + numOfPax +
                ", hotelId=" + hotelId +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                '}';
    }
}
