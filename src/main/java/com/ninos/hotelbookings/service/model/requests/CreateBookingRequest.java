package com.ninos.hotelbookings.service.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public final class CreateBookingRequest {

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

    @Override
    public String toString() {
        return "CreateBookingRequest{" +
                "customerName='" + customerName + '\'' +
                ", customerSurname='" + customerSurname + '\'' +
                ", numOfPax=" + numOfPax +
                '}';
    }
}
