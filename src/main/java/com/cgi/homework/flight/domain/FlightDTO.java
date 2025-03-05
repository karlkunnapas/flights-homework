package com.cgi.homework.flight.domain;

import java.math.BigDecimal;
import java.util.Date;

public record FlightDTO(String destination,
String startingPoint,
        Date date,
        int flightTime,
        BigDecimal price,
        int freeSpots) {
}
