package com.cgi.homework.flight;
import com.cgi.homework.flight.domain.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/flight")
    public List<FlightDTO> getTestData() {
        return flightService.generateFlights();
    }
}