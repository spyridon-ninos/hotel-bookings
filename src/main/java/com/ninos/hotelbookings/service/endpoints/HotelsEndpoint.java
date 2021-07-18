package com.ninos.hotelbookings.service.endpoints;

import com.ninos.hotelbookings.core.business.api.BookingService;
import com.ninos.hotelbookings.core.business.api.HotelService;
import com.ninos.hotelbookings.core.business.model.Booking;
import com.ninos.hotelbookings.service.endpoints.documentation.HotelsEndpointDocumentation;
import com.ninos.hotelbookings.service.model.requests.CreateBookingRequest;
import com.ninos.hotelbookings.service.model.requests.CreateHotelRequest;
import com.ninos.hotelbookings.service.model.requests.UpdateBookingRequest;
import com.ninos.hotelbookings.service.model.requests.UpdateHotelRequest;
import com.ninos.hotelbookings.service.model.responses.BookingResponse;
import com.ninos.hotelbookings.service.model.responses.HotelResponse;
import com.ninos.hotelbookings.service.model.responses.TotalBookingsPriceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * endpoints related to the hotels of the system
 */
@RestController
@RequestMapping(
        path = "/api/v1/hotels",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class HotelsEndpoint implements HotelsEndpointDocumentation {

    private final Logger logger = LoggerFactory.getLogger(HotelsEndpoint.class);

    private final HotelService hotelService;
    private final BookingService bookingService;

    @Autowired
    public HotelsEndpoint(
            HotelService hotelService,
            BookingService bookingService
    ) {
        this.hotelService = hotelService;
        this.bookingService = bookingService;
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createHotel(
            @RequestBody @Valid final CreateHotelRequest createHotelRequest
    ) {

        logger.info("Creating a new hotel: {}", createHotelRequest);

        var createdHotel = hotelService.createHotel(createHotelRequest.toModel());

        logger.debug("Created: {}", createdHotel);

        try {
            var returnUri = new URI("/api/v1/hotels/" + createdHotel.getId());
            return ResponseEntity.created(returnUri)
                                 .build();
        } catch (Exception ex) {
            logger.error("Got exception: {}", ex.getMessage());
            logger.debug("{}", ex.getMessage(), ex);

            // 204 because the entity has already been saved and the
            // return uri has not been created correctly
            return ResponseEntity.noContent()
                                 .build();
        }
    }

    @Override
    @PatchMapping(
            path = "/{hotelId}",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HotelResponse> updateHotel(
            @PathVariable("hotelId") @Min(0) final long hotelId,
            @RequestBody @Valid final UpdateHotelRequest updateHotelRequest
    ) {

        logger.info("Updating hotel with id: {} with data: {}", hotelId, updateHotelRequest);


        var hotel = hotelService.getHotel(hotelId);

        if (hotel.isEmpty()) {
            logger.error("Hotel with id: {} was not found", hotelId);
            return ResponseEntity.notFound()
                                 .build();
        }

        final var newAddress = updateHotelRequest.getHotelAddress();
        final var newStars = updateHotelRequest.getStarRating();

        if (newAddress == null && newStars == null) {
            logger.error("Nothing to update. Skipping step.");
            return ResponseEntity.badRequest()
                                 .build();
        }

        var hotelToUpdate = hotel.get();
        if (newAddress != null) {
            hotelToUpdate.setAddress(newAddress);
        }

        if (newStars != null) {
            hotelToUpdate.setStarRating(newStars);
        }

        var updatedHotel = hotelService.updateHotel(hotelToUpdate);

        return ResponseEntity.ok(new HotelResponse(updatedHotel));
    }

    @Override
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Void> deleteHotel(
            @PathVariable("hotelId") @NotEmpty @NotBlank final long hotelId
    ) {

        logger.info("Deleting hotel with id: {}", hotelId);

        if (hotelService.deleteHotel(hotelId)) {
            return ResponseEntity.noContent()
                                 .build();
        } else {
            logger.error("Hotel with id: {} was not found", hotelId);

            return ResponseEntity.notFound()
                                 .build();
        }
    }

    @Override
    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponse> getHotel(
            @PathVariable("hotelId") @Min(0) final long hotelId
    ) {

        var hotel = hotelService.getHotel(hotelId);

        return hotel.map(value -> ResponseEntity.ok(new HotelResponse(value)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping
    public ResponseEntity<List<HotelResponse>> getAllHotels() {

        var hotels = hotelService.getAllHotels()
                                 .stream()
                                 .map(HotelResponse::new)
                                 .collect(Collectors.toList());

        return ResponseEntity.ok(hotels);
    }

    @Override
    @GetMapping("/{hotelId}/bookings")
    public ResponseEntity<List<BookingResponse>> getHotelBookings(
            @PathVariable("hotelId") @Min(0) final long hotelId
    ) {

        if (hotelService.getHotel(hotelId).isEmpty()) {
            logger.error("Hotel with id: {} was not found", hotelId);
            return ResponseEntity.notFound()
                                 .build();
        }

        var response = hotelService.getBookings(hotelId)
                                   .stream()
                                   .map(BookingResponse::new)
                                   .collect(Collectors.toList());

        logger.debug("Returning bookings for hotel with id: {}: {}", hotelId, response);

        return ResponseEntity.ok(response);
    }

    @Override
    @PostMapping(
            path = "/{hotelId}/bookings",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BookingResponse> addHotelBooking(
            @PathVariable("hotelId") @Min(0) final long hotelId,
            @RequestBody @Valid CreateBookingRequest createBookingRequest
    ) {

        logger.info("Adding a hotel (id: {}) booking: {}", hotelId, createBookingRequest);

        if (hotelService.getHotel(hotelId).isEmpty()) {
            logger.error("Hotel with id: {} was not found", hotelId);
            return ResponseEntity.notFound()
                                 .build();
        }

        var bookingToAdd = new Booking();
        bookingToAdd.setCustomerName(createBookingRequest.getCustomerName());
        bookingToAdd.setCustomerSurname(createBookingRequest.getCustomerSurname());
        bookingToAdd.setNumOfPax(createBookingRequest.getNumOfPax());
        bookingToAdd.setPrice(50 * bookingToAdd.getNumOfPax());
        bookingToAdd.setCurrency("EUR");

        var savedBooking = hotelService.addBooking(hotelId, bookingToAdd);

        logger.debug("Saved booking: {}", savedBooking);

        return ResponseEntity.ok(new BookingResponse(savedBooking));
    }

    @Override
    @GetMapping(path = "/{hotelId}/bookings/total-price")
    public ResponseEntity<TotalBookingsPriceResponse> getHotelTotalBookingPrice(
            @PathVariable("hotelId") @Min(0) final long hotelId
    ) {
        if (hotelService.getHotel(hotelId).isEmpty()) {
            logger.error("Hotel with id: {} was not found", hotelId);
            return ResponseEntity.notFound()
                                 .build();
        }

        var totalPrice = hotelService.getBookings(hotelId)
                                   .stream()
                                   .mapToInt(Booking::getPrice)
                                   .sum();

        logger.debug("Returning the total price for all the bookings for hotel with id: {}: {}", hotelId, totalPrice);

        return ResponseEntity.ok(new TotalBookingsPriceResponse(totalPrice));
    }

    @Override
    @DeleteMapping(path = "/{hotelId}/bookings/{bookingId}")
    public ResponseEntity<Void> cancelHotelBooking(
            @PathVariable("hotelId") @NotEmpty final long hotelId,
            @PathVariable("bookingId") @NotEmpty final long bookingId
    ) {

        logger.info("Cancelling the booking with id: {} from hotel with id: {}", bookingId, hotelId);

        if (hotelService.deleteHotelBooking(hotelId, bookingId)) {
            return ResponseEntity.noContent()
                                 .build();
        } else {
            logger.error("Hotel with id: {} was not found", hotelId);

            return ResponseEntity.notFound()
                                 .build();
        }
    }

    @Override
    @PatchMapping(path = "/{hotelId}/bookings/{bookingId}")
    public ResponseEntity<BookingResponse> updateHotelBooking(
            @PathVariable("hotelId") @NotEmpty final long hotelId,
            @PathVariable("bookingId") @NotEmpty final long bookingId,
            @RequestBody @Valid final UpdateBookingRequest updateBookingRequest
    ) {

        logger.info("Updating the booking with id {}", bookingId);

        var booking = bookingService.update(bookingId, updateBookingRequest.getNumOfPax());

        if (booking.isPresent()) {
            return ResponseEntity.ok(new BookingResponse(booking.get()));
        } else {
            logger.error("Hotel with id: {} was not found", hotelId);

            return ResponseEntity.notFound()
                                 .build();
        }
    }
}
