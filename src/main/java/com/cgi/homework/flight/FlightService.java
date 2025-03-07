package com.cgi.homework.flight;

import com.cgi.homework.flight.domain.FlightDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class FlightService {
    public List<FlightDTO> flights;

    public List<FlightDTO> generateFlights(String dateFrom, String dateTo, int people) {
        Random random = new Random();
        LocalDate from = LocalDate.parse(dateFrom);
        LocalDate to = LocalDate.parse(dateTo);
        List<String> cities = new ArrayList<>();
        List<LocalTime> times = new ArrayList<>();
        cities.add("Tallinn");
        cities.add("Barcelona");
        cities.add("Tenerife");
        cities.add("London");
        cities.add("Milano");
        cities.add("Pariis");
        cities.add("Frankfurt");
        times.add(LocalTime.of(14, 0));
        times.add(LocalTime.of(20, 0));
        flights = new ArrayList<>();
        while (from.isBefore(to) || from.equals(to)) {
            LocalDateTime time1 = LocalDateTime.of(from, LocalTime.of(14, 0));
            LocalDateTime time2 = LocalDateTime.of(from, LocalTime.of(20, 0));
            for (int i = 0; i < cities.size(); i++) {
                for (int j = i + 1; j < cities.size(); j++) {
                    flights.add(new FlightDTO(cities.get(i), cities.get(j), time1, 120, new BigDecimal("135").multiply(BigDecimal.valueOf(people)), random.ints(0, 218).findFirst().getAsInt()));
                    flights.add(new FlightDTO(cities.get(cities.size() - (i + 1)), cities.get(cities.size() - (j + 1)), time1, 120, new BigDecimal("135").multiply(BigDecimal.valueOf(people)), random.ints(0, 218).findFirst().getAsInt()));

                    flights.add(new FlightDTO(cities.get(i), cities.get(j), time2, 120, new BigDecimal("135").multiply(BigDecimal.valueOf(people)), random.ints(0, 218).findFirst().getAsInt()));
                    flights.add(new FlightDTO(cities.get(cities.size() - (i + 1)), cities.get(cities.size() - (j + 1)), time2, 120, new BigDecimal("135").multiply(BigDecimal.valueOf(people)), random.ints(0, 218).findFirst().getAsInt()));
                }
            }
            from = from.plusDays(1);
        }
        return flights;
    }

    public List<FlightDTO> filterFlights(String startingPoint, String destination, LocalDate date, int flightTime, BigDecimal price) {
        List<FlightDTO> filteredFlights = new ArrayList<>();
        for (FlightDTO flight : flights) {
            if (flight.startingPoint().equals(startingPoint) && flight.destination().equals(destination) && flight.date().getDayOfMonth() == date.getDayOfMonth() && flight.flightTime() <= flightTime && flight.price().compareTo(price) <= 0) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }
}
