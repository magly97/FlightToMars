package com.mars.flights.controllers;

import com.mars.flights.models.BookingList;
import com.mars.flights.models.Flight;
import com.mars.flights.models.Passenger;
import com.mars.flights.repositories.BookingListRepository;
import com.mars.flights.repositories.FlightRepository;
import com.mars.flights.repositories.PassengerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerController {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BookingListRepository bookingListRepository;

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping
    public List<Passenger> list()
    {
        return passengerRepository.findAll();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create_passenger(@RequestBody Passenger passenger)
    {
        passengerRepository.save(passenger);
    }
    @DeleteMapping("/{id}")
    public void delete_passenger(@PathVariable("id") Long id)
    {
        List<BookingList> passengerBooking = bookingListRepository.findByIdPassenger(id);
        Flight existingFlight;
        for (BookingList bl: passengerBooking) {
            existingFlight = flightRepository.getOne(bl.getIdFlight());
            existingFlight.updateNumberOfPassengersMinus();
            flightRepository.saveAndFlush(existingFlight);
            bookingListRepository.deleteById(bl.getId());
        }
        passengerRepository.deleteById(id);
    }
    @GetMapping("/{id}")
    public Passenger get(@PathVariable("id") Long id)
    {
        return passengerRepository.getOne(id);
    }

    @PutMapping("/{id}")
    public  Passenger update(@PathVariable Long id, @RequestBody Passenger passenger)
    {
        Passenger existingPassenger = passengerRepository.getOne(id);
        BeanUtils.copyProperties(passenger,existingPassenger);
        return passengerRepository.saveAndFlush(existingPassenger);
    }
}
