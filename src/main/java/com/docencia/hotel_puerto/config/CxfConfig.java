package com.docencia.hotel_puerto.config;

import com.docencia.hotel_puerto.web.soap.GuestSoapService;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig {
    private final Bus bus;
    private final GuestSoapService guestSoapService;

    public CxfConfig(Bus bus, GuestSoapService guestSoapService) {
        this.bus = bus;
        this.guestSoapService = guestSoapService;
    }

    @Bean
    public Endpoint guestEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, guestSoapService);
        endpoint.publish("/guest");
        return endpoint;
    }
}
