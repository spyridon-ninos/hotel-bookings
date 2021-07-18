package com.ninos.hotelbookings.core.business.api;

import com.ninos.hotelbookings.core.business.model.Booking;
import com.ninos.hotelbookings.core.business.spi.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * the service that handles all related functionality of the bookings taking place in our systems
 */
@Service
public class BookingService {

    private final Logger logger = LoggerFactory.getLogger(BookingService.class);

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(
            BookingRepository bookingRepository
    ) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getBookingsBySurname(final String customerSurname) {
        return bookingRepository.findBySurname(customerSurname);
    }

    public Optional<Booking> update(final long bookingId, final int numOfPax) {
        var bookingOptional = bookingRepository.findById(bookingId);

        if (bookingOptional.isEmpty()) {
            return Optional.empty();
        }

        return bookingRepository.update(bookingId, numOfPax);
    }
}
