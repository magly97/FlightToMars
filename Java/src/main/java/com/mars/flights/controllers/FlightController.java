package com.mars.flights.controllers;

import com.mars.flights.models.BookingList;
import com.mars.flights.models.Flight;
import com.mars.flights.repositories.BookingListRepository;
import com.mars.flights.repositories.FlightRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    BookingListRepository bookingListRepository;

    @GetMapping
    public List<Flight> list()
    {
        return flightRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean create_flight(@RequestBody Flight flight)
    {
        flightRepository.saveAndFlush(flight);
        return true;
    }

    @GetMapping("/{id}")
    public Flight get_flight(@PathVariable("id") long id)
    {
        return flightRepository.getOne(id);
    }

    @DeleteMapping("/{id}")
    public void delete_flight(@PathVariable("id") Long id)
    {
        List<BookingList> Booking = bookingListRepository.findByIdFlight(id);
        for (BookingList bl:Booking)
        {
            bookingListRepository.deleteById(bl.getId());
        }
        flightRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id,@RequestBody Flight flight)
    {
        Flight existingFlight = flightRepository.getOne(id);
        BeanUtils.copyProperties(flight,existingFlight);
        flightRepository.saveAndFlush(existingFlight);
    }

}
