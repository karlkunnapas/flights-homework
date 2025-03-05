package com.cgi.homework.flight;

import com.cgi.homework.flight.domain.FlightDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class FlightService {
    public List<FlightDTO> generateFlights() {
        return List.of(new FlightDTO("something", "Tallinn", new Date(), 120, BigDecimal.ONE, 12)); // @todo genereeri lennud
    }
}
