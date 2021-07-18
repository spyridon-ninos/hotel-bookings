package com.ninos.hotelbookings.service.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public final class UpdateBookingRequest {

    @JsonProperty("num_of_pax")
    @NotNull
    private int numOfPax;

    public int getNumOfPax() {
        return numOfPax;
    }

    public void setNumOfPax(final int numOfPax) {
        this.numOfPax = numOfPax;
    }

    @Override
    public String toString() {
        return "UpdateBookingRequest{" +
                "numOfPax=" + numOfPax +
                '}';
    }
}
