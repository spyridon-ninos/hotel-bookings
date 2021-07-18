package com.ninos.hotelbookings.service.endpoints;

import com.ninos.hotelbookings.core.business.api.BookingService;
import com.ninos.hotelbookings.service.endpoints.documentation.CustomersEndpointDocumentation;
import com.ninos.hotelbookings.service.model.responses.BookingResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(
        path = "/api/v1/customers",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class CustomersEndpoint implements CustomersEndpointDocumentation {

    private final Logger logger = LoggerFactory.getLogger(CustomersEndpoint.class);

    private final BookingService bookingService;

    @Autowired
    public CustomersEndpoint(
            BookingService bookingService
    ) {
        this.bookingService = bookingService;
    }

    @Override
    @GetMapping(path = "/{surname}/bookings")
    public ResponseEntity<List<BookingResponse>> getBookings(
            @PathVariable("surname") @NotEmpty @NotBlank final String customerSurname
    ) {

        logger.info("Fetching all bookings for surname {}", customerSurname);

        List<BookingResponse> bookings = bookingService.getBookingsBySurname(customerSurname)
                                                       .stream()
                                                       .map(BookingResponse::new)
                                                       .collect(Collectors.toList());

        logger.debug("Returning: {}", bookings);

        return ResponseEntity.ok(bookings);
    }
}
