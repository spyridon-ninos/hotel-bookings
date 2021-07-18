package com.ninos.hotelbookings.core.business.spi;

import com.ninos.hotelbookings.core.business.model.Booking;
import com.ninos.hotelbookings.core.business.model.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelRepository {
    Hotel save(Hotel hotel);
    List<Hotel> findAll();
    boolean delete(final long hotelId);
    Optional<Hotel> get(final long hotelId);
    Hotel update(final Hotel hotel);
    void addBooking(final long hotelId, Booking booking);
    void deleteBooking(final long hotelId, final Booking booking);
}
