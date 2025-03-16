package com.cgi.homework.flight;

import com.cgi.homework.flight.domain.FlightDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class FlightService {
    public List<FlightDTO> flights;

    // flights generator
    public List<FlightDTO> generateFlights(LocalDate dateFrom, LocalDate dateTo, int people) {
        Random random = new Random();
        List<String> cities = new ArrayList<>();
        List<LocalTime> times = new ArrayList<>();

        // adding cities for the flights
        cities.add("Tallinn");
        cities.add("Barcelona");
        cities.add("Tenerife");
        cities.add("London");
        cities.add("Milan");
        cities.add("Paris");
        cities.add("Frankfurt");

        // adding times at which flights occur
        times.add(LocalTime.of(14, 0));
        times.add(LocalTime.of(20, 0));

        // array to store generated flights
        flights = new ArrayList<>();

        // flights are generated from the cities list moving from top to bottom and bottom to top pairing each city with the city following it.
        while (dateFrom.isBefore(dateTo) || dateFrom.equals(dateTo)) {
            LocalDateTime time1 = LocalDateTime.of(dateFrom, LocalTime.of(14, 0));
            LocalDateTime time2 = LocalDateTime.of(dateFrom, LocalTime.of(20, 0));
            for (int i = 0; i < cities.size(); i++) {
                for (int j = i + 1; j < cities.size(); j++) {
                    // random price from 40 to 300
                    BigDecimal price = BigDecimal.valueOf(random.ints(40, 300).findFirst().getAsInt());

                    // random number of free spots ranging from 0 to 218
                    int freeSpots = random.ints(0, 218).findFirst().getAsInt();

                    // 2 flights at 2 different hours of day, coming from top to bottom in cities list
                    flights.add(new FlightDTO(cities.get(i), cities.get(j), time1, 135, price.multiply(BigDecimal.valueOf(people)), freeSpots));
                    price = BigDecimal.valueOf(random.ints(40, 300).findFirst().getAsInt());
                    freeSpots = random.ints(0, 218).findFirst().getAsInt();
                    flights.add(new FlightDTO(cities.get(i), cities.get(j), time2, 120, price.multiply(BigDecimal.valueOf(people)), freeSpots));


                    // 2 flights at 2 different hours of day, coming from bottom to top in cities list
                    price = BigDecimal.valueOf(random.ints(40, 300).findFirst().getAsInt());
                    freeSpots = random.ints(0, 218).findFirst().getAsInt();
                    flights.add(new FlightDTO(cities.get(cities.size() - (i + 1)), cities.get(cities.size() - (j + 1)), time1, 140, price.multiply(BigDecimal.valueOf(people)), freeSpots));
                    price = BigDecimal.valueOf(random.ints(40, 300).findFirst().getAsInt());
                    freeSpots = random.ints(0, 218).findFirst().getAsInt();
                    flights.add(new FlightDTO(cities.get(cities.size() - (i + 1)), cities.get(cities.size() - (j + 1)), time2, 145, price.multiply(BigDecimal.valueOf(people)), freeSpots));
                }
            }
            dateFrom = dateFrom.plusDays(1);
        }
        return flights;
    }

    // filters generated flights based on given parameters
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
