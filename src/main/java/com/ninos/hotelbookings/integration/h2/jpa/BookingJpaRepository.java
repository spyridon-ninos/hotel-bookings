package com.ninos.hotelbookings.integration.h2.jpa;

import com.ninos.hotelbookings.integration.h2.jpa.entities.BookingJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingJpaRepository extends JpaRepository<BookingJpaEntity, Long> {
    List<BookingJpaEntity> findAllByHotelId(final long hotelId);
    List<BookingJpaEntity> findByCustomerSurname(final String customerSurname);
}
