package com.ninos.hotelbookings.service.endpoints.documentation;

import com.ninos.hotelbookings.service.model.requests.CreateBookingRequest;
import com.ninos.hotelbookings.service.model.requests.CreateHotelRequest;
import com.ninos.hotelbookings.service.model.requests.UpdateBookingRequest;
import com.ninos.hotelbookings.service.model.requests.UpdateHotelRequest;
import com.ninos.hotelbookings.service.model.responses.BookingResponse;
import com.ninos.hotelbookings.service.model.responses.HotelResponse;
import com.ninos.hotelbookings.service.model.responses.TotalBookingsPriceResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface HotelsEndpointDocumentation {

/*
 * createHotel
 */
    @Operation(summary = "Create a hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel created"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    ResponseEntity<Void> createHotel(final CreateHotelRequest createHotelRequest);

/*
 * updateHotel
 */
    @Operation(summary = "Update a hotel's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The hotel's details were updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    ResponseEntity<HotelResponse> updateHotel(final long hotelId, final UpdateHotelRequest updateHotelRequest);

/*
 * deleteHotel
 */
    @Operation(summary = "Delete a hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The hotel was deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    ResponseEntity<Void> deleteHotel(final long hotelId);

/*
 * getHotel
 */
    @Operation(summary = "Get a hotel's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The details of the hotel are returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    ResponseEntity<HotelResponse> getHotel(final long hotelId);

/*
 * getAllHotels
 */
    @Operation(summary = "Get all hotels' details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The details of the hotels are returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    ResponseEntity<List<HotelResponse>> getAllHotels();

/*
 * getHotelBookings
 */
    @Operation(summary = "Get all hotels' bookings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The bookings of the hotels are returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    ResponseEntity<List<BookingResponse>> getHotelBookings(final long hotelId);

/*
 * addHotelBooking
 */
    @Operation(summary = "Add a booking to a hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added a booking to a hotel"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    ResponseEntity<BookingResponse> addHotelBooking(final long hotelId, CreateBookingRequest createBookingRequest);

/*
 * getHotelTotalBookingPrice
 */
    @Operation(summary = "Get the total price of a hotels' bookings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The total bookings price is returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    ResponseEntity<TotalBookingsPriceResponse> getHotelTotalBookingPrice(final long hotelId);

/*
 * cancelHotelBooking
 */
    @Operation(summary = "Delete a hotel's booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The hotel was deleted"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Hotel not found")
    })
    ResponseEntity<Void> cancelHotelBooking(final long hotelId, final long bookingId);

/*
 * updateHotelBooking
 */
    @Operation(summary = "Update a hotel's booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The booking was updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    ResponseEntity<BookingResponse> updateHotelBooking(final long hotelId, final long bookingId, final UpdateBookingRequest updateBookingRequest);
}