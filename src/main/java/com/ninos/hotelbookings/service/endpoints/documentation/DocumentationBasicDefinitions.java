package com.ninos.hotelbookings.service.endpoints.documentation;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Hotel Bookings API",
                version = "v1",
                contact = @Contact(
                        name = "Spyridon Ninos",
                        url = "https://<removed>.gr",
                        email = "<removed>"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://github.com/spyridon-ninos/hotel-bookings/blob/master/LICENSE.txt"
                )
        ),
        servers = @Server(url = "http://localhost:5000")
)
public class DocumentationBasicDefinitions {
}
