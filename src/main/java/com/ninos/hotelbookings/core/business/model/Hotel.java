package com.ninos.hotelbookings.core.business.model;

import java.util.Objects;

public final class Hotel {

    private long id;
    private String name;
    private String address;
    private int starRating;

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(final int starRating) {
        this.starRating = starRating;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Hotel hotel = (Hotel) o;
        return id == hotel.id &&
                starRating == hotel.starRating &&
                Objects.equals(name, hotel.name) &&
                Objects.equals(address, hotel.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, starRating);
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", starRating=" + starRating +
                '}';
    }
}
