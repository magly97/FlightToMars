package com.mars.flights.controllers;

import com.mars.flights.models.Passenger;
import com.mars.flights.repositories.PassengerRepository;
import com.mars.flights.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping
    public List<Passenger> passengersList() {
        return passengerService.passengersList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createPassenger(@RequestBody Passenger passenger) {
        passengerService.createPassenger(passenger);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable("id") Long id) {
        passengerService.deletePassenger(id);
    }

    @GetMapping("/{id}")
    public Passenger getPassenger(@PathVariable("id") Long id) {
        return  passengerService.getPassenger(id);
    }

    @PutMapping("/{id}")
    public  Passenger updatePassenger(@PathVariable Long id, @RequestBody Passenger passenger) {
        return passengerService.updatePassenger(id, passenger);
    }
}
