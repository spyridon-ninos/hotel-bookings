package com.ninos.hotelbookings.core.business.api;

import com.ninos.hotelbookings.core.business.model.Booking;
import com.ninos.hotelbookings.core.business.model.Hotel;
import com.ninos.hotelbookings.core.business.spi.BookingRepository;
import com.ninos.hotelbookings.core.business.spi.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * class that handles all requests around the hotels of the system
 */
@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public HotelService(
            HotelRepository hotelRepository,
            BookingRepository bookingRepository
    ) {
        this.hotelRepository = hotelRepository;
        this.bookingRepository = bookingRepository;
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public boolean deleteHotel(final long hotelId) {
        return hotelRepository.delete(hotelId);
    }

    public Optional<Hotel> getHotel(final long hotelId) {
        return hotelRepository.get(hotelId);
    }

    public Hotel updateHotel(final Hotel hotel) {
        return hotelRepository.update(hotel);
    }

    public List<Booking> getBookings(final long hotelId) {
        return bookingRepository.findAll(hotelId);
    }

    public Booking addBooking(final long hotelId, final Booking booking) {

        Predicate<Booking> matchBooking = book -> book.getCustomerName().equals(booking.getCustomerName())
                                                    && book.getCustomerSurname().equals(booking.getCustomerSurname())
                                                    && book.getNumOfPax() == booking.getNumOfPax();

        hotelRepository.addBooking(hotelId, booking);

        return bookingRepository.findAll(hotelId)
                                .stream()
                                .filter(matchBooking)
                                .collect(Collectors.toList())
                                .get(0); // we don't expect more than one element, because
                                         // (name, username, hotelId) is unique per hotel (see db schema)
    }

    public boolean deleteHotelBooking(final long hotelId, final long bookingId) {
        var booking = bookingRepository.findById(bookingId);

        if (booking.isEmpty()) {
            return false;
        }

        var hotel = hotelRepository.get(hotelId);

        if (hotel.isEmpty()) {
            return false;
        }

        bookingRepository.delete(bookingId);
        hotelRepository.deleteBooking(hotelId, booking.get());

        return true;
    }
}
