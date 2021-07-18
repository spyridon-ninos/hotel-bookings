package com.ninos.hotelbookings.core.business.model;

import java.util.Objects;

public final class Booking {

    private long id;
    private String customerName;
    private String customerSurname;
    private int numOfPax;
    private long hotelId;
    private int price;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Booking booking = (Booking) o;
        return id == booking.id &&
                numOfPax == booking.numOfPax &&
                hotelId == booking.hotelId &&
                price == booking.price &&
                Objects.equals(customerName, booking.customerName) &&
                Objects.equals(customerSurname, booking.customerSurname) &&
                Objects.equals(currency, booking.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, customerSurname, numOfPax, hotelId, price, currency);
    }

    @Override
    public String toString() {
        return "Booking{" +
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
