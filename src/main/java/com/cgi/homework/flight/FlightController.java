package com.cgi.homework.flight;
import com.cgi.homework.flight.domain.FlightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping(value = "/flight")
    public List<FlightDTO> getTestData() {
        String from = "2025-03-08";
        String to = "2025-03-10";
        return flightService.generateFlights(from, to, 1);
    }

    @GetMapping(value = "/flight/filter")
    public List<FlightDTO> filterData() {
        return flightService.filterFlights("Barcelona", "Tallinn", LocalDate.of(25, 3, 9), 140, BigDecimal.valueOf(150));
    }
}