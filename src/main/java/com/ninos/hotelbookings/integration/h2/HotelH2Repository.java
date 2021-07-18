package com.ninos.hotelbookings.integration.h2;

import com.ninos.hotelbookings.core.business.model.Booking;
import com.ninos.hotelbookings.core.business.model.Hotel;
import com.ninos.hotelbookings.core.business.spi.HotelRepository;
import com.ninos.hotelbookings.integration.h2.jpa.HotelJpaRepository;
import com.ninos.hotelbookings.integration.h2.jpa.entities.BookingJpaEntity;
import com.ninos.hotelbookings.integration.h2.jpa.entities.HotelJpaEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class HotelH2Repository implements HotelRepository {

    private final Logger logger = LoggerFactory.getLogger(HotelH2Repository.class);

    private final HotelJpaRepository hotelJpaRepository;

    @Autowired
    public HotelH2Repository(
            HotelJpaRepository hotelJpaRepository
    ) {
        this.hotelJpaRepository = hotelJpaRepository;
    }

    @Override
    public Hotel save(final Hotel hotel) {
        var hotelJpaEntity = hotelJpaRepository.save(new HotelJpaEntity(hotel));
        return hotelJpaEntity.toModel();
    }

    @Override
    public List<Hotel> findAll() {
        var hotelJpaEntities = hotelJpaRepository.findAll();

        return hotelJpaEntities.stream()
                               .map(HotelJpaEntity::toModel)
                               .collect(Collectors.toList());
    }

    @Override
    public boolean delete(final long hotelId) {
        var hotelToDelete = hotelJpaRepository.findById(hotelId);

        if (hotelToDelete.isPresent()) {
            hotelJpaRepository.delete(hotelToDelete.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<Hotel> get(final long hotelId) {
        return hotelJpaRepository.findById(hotelId)
                                 .map(HotelJpaEntity::toModel);
    }

    @Override
    public Hotel update(final Hotel hotel) {
        var hotelJpaEntity = hotelJpaRepository.findById(hotel.getId())
                                               .orElseThrow(IllegalArgumentException::new);

        hotelJpaEntity.setAddress(hotel.getAddress());
        hotelJpaEntity.setStarRanking(hotel.getStarRating());

        var savedHotel = hotelJpaRepository.save(hotelJpaEntity);

        return savedHotel.toModel();
    }

    @Override
    public void addBooking(final long hotelId, final Booking booking) {
        var hotelJpaEntity = hotelJpaRepository.findById(hotelId)
                                               .orElseThrow(IllegalArgumentException::new);

        var bookingJpaEntity = new BookingJpaEntity(booking);
        hotelJpaEntity.addBooking(bookingJpaEntity);
        try {
            hotelJpaRepository.save(hotelJpaEntity);
        } catch (Exception ex) {
            logger.error("Could not save booking: {}", ex.getMessage());
            logger.debug("{} {}", ex.getMessage(), booking, ex);
        }
    }

    @Override
    public void deleteBooking(final long hotelId, final Booking booking) {
        var hotelJpaEntity = hotelJpaRepository.findById(hotelId)
                                               .orElseThrow(IllegalArgumentException::new);

        var bookingJpaEntity = new BookingJpaEntity(booking);
        hotelJpaEntity.removeBooking(bookingJpaEntity);
        try {
            hotelJpaRepository.save(hotelJpaEntity);
        } catch (Exception ex) {
            logger.error("Could not delete booking: {}", ex.getMessage());
            logger.debug("{} {}", ex.getMessage(), booking, ex);
        }
    }
}
