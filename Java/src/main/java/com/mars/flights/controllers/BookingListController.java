package com.mars.flights.controllers;

import com.mars.flights.models.BookingList;
import com.mars.flights.models.Flight;
import com.mars.flights.models.Passenger;
import com.mars.flights.services.BookingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/booking")
public class BookingListController {

    @Autowired
    private BookingListService bookingListService;

    @GetMapping("/passengers/{id}")
    public List<Flight> ListByPassengerId(@PathVariable Long id)
    {
        return bookingListService.ListByPassengerId(id);
    }

    @GetMapping("/flights/{id}")
    public List<Passenger> ListByFlightId(@PathVariable Long id)
    {
        return bookingListService.ListByFlightId(id);
    }

    @DeleteMapping("/del/{idFlight}/{idPassenger}")
    public void deleteBookingList(@PathVariable Long idFlight, @PathVariable Long idPassenger) {
        bookingListService.deleteBookingList(idFlight, idPassenger);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createBookingList(@RequestBody BookingList bookingList) {
        bookingListService.createBookingList(bookingList);
    }
}
