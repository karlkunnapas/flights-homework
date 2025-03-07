package com.cgi.homework.flight.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.time.LocalDateTime;

public record FlightDTO(String startingPoint,
        String destination,
        LocalDateTime date,
        int flightTime,
        BigDecimal price,
        int freeSpots) {
}
