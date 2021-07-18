package com.ninos.hotelbookings.core.business.spi;

import com.ninos.hotelbookings.core.business.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {
    List<Booking> findAll(final long hotelId);
    List<Booking> findBySurname(final String customerSurname);
    Optional<Booking> findById(final long bookingId);
    void delete(final long bookingId);
    Optional<Booking> update(final long bookingId, final int numOfPax);
}
