package com.mars.flights.controllers;

import com.mars.flights.models.Flight;
import com.mars.flights.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> flightList() {
        return flightService.flightList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createFlight(@RequestBody Flight flight) {
        flightService.createFlight(flight);
    }

    @GetMapping("/{id}")
    public Flight getFlight(@PathVariable("id") long id) {
        return flightService.getFlight(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFlight(@PathVariable("id") Long id) {
        flightService.deleteFlight(id);
    }

    @PutMapping("/{id}")
    public void updateFlight(@PathVariable("id") Long id,@RequestBody Flight flight) {
        flightService.updateFlight(id, flight);
    }

}
