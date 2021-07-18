package com.ninos.hotelbookings.service.model.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public final class TotalBookingsPriceResponse {

    @JsonProperty("total_price")
    @NotNull
    private int totalPrice;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(final int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TotalBookingsPriceResponse() {
    }

    public TotalBookingsPriceResponse(@NotNull final int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "TotalBookingsPriceResponse{" +
                "totalPrice=" + totalPrice +
                '}';
    }
}
