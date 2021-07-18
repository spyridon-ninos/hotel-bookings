package com.ninos.hotelbookings.service.endpoints.documentation;

import com.ninos.hotelbookings.service.model.responses.BookingResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CustomersEndpointDocumentation {

    @Operation(summary = "Get a customer's bookings by his surname")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The bookings of the customer are returned"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    ResponseEntity<List<BookingResponse>> getBookings(final String customerSurname);
}
