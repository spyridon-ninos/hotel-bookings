package com.ninos.hotelbookings.integration.h2;

import com.ninos.hotelbookings.core.business.model.Booking;
import com.ninos.hotelbookings.core.business.spi.BookingRepository;
import com.ninos.hotelbookings.integration.h2.jpa.BookingJpaRepository;
import com.ninos.hotelbookings.integration.h2.jpa.entities.BookingJpaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookingH2Repository implements BookingRepository {

    private final BookingJpaRepository bookingJpaRepository;

    @Autowired
    public BookingH2Repository(
            BookingJpaRepository bookingJpaRepository
    ) {
        this.bookingJpaRepository = bookingJpaRepository;
    }

    @Override
    public List<Booking> findAll(final long hotelId) {
        return bookingJpaRepository.findAllByHotelId(hotelId)
                                   .stream()
                                   .map(BookingJpaEntity::toModel)
                                   .collect(Collectors.toList());
    }

    @Override
    public List<Booking> findBySurname(final String customerSurname) {
        return bookingJpaRepository.findByCustomerSurname(customerSurname)
                                   .stream()
                                   .map(BookingJpaEntity::toModel)
                                   .collect(Collectors.toList());
    }

    @Override
    public Optional<Booking> findById(final long bookingId) {
        return bookingJpaRepository.findById(bookingId)
                                   .map(BookingJpaEntity::toModel);
    }

    @Override
    public void delete(final long bookingId) {
        bookingJpaRepository.deleteById(bookingId);
    }

    @Override
    public Optional<Booking> update(final long bookingId, final int numOfPax) {

        var optionalBookingJpaEntity = bookingJpaRepository.findById(bookingId);

        if (optionalBookingJpaEntity.isEmpty()) {
            return Optional.empty();
        }

        var bookingJpaEntity = optionalBookingJpaEntity.get();
        bookingJpaEntity.setNumOfPax(numOfPax);
        bookingJpaEntity.setPrice(50 * numOfPax);
        var savedBooking = bookingJpaRepository.save(bookingJpaEntity);

        return Optional.of(savedBooking.toModel());
    }
}
