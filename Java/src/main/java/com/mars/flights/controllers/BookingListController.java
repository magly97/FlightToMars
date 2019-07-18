package com.mars.flights.controllers;

import com.mars.flights.models.Flight;
import com.mars.flights.models.Passenger;
import com.mars.flights.services.FlightService;
import com.mars.flights.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/booking")
public class BookingListController {

    @Autowired
    private FlightService flightService;
    @Autowired
    private PassengerService passengerService;

    @GetMapping("/passengers/{id}")
    public Set<Flight> ListByPassengerId(@PathVariable Long id) {

        return passengerService.flightsPassenger(id);
    }

    @GetMapping("/flights/{id}")
    public Set<Passenger> ListByFlightId(@PathVariable Long id) {
        return flightService.passengersFlight(id);
    }

    @DeleteMapping("/del/{flightId}/{passengerId}")
    public void deleteBookingList(@PathVariable Long flightId, @PathVariable Long passengerId) {
        flightService.deleteBooking(passengerId, flightId);
    }

    @PostMapping("/{flightId}/{passengerId}")
    @ResponseStatus(HttpStatus.OK)
    public void createBookingList(@PathVariable Long flightId, @PathVariable Long passengerId) {
        flightService.createBooking(passengerId, flightId);
    }
}
