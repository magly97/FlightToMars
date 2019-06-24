package com.mars.flights.controllers;


import com.mars.flights.models.BookingList;
import com.mars.flights.models.Flight;
import com.mars.flights.models.Passenger;
import com.mars.flights.repositories.BookingListRepository;
import com.mars.flights.repositories.FlightRepository;
import com.mars.flights.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
public class BookingListController {
    @Autowired
    private BookingListRepository bookingListRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PassengerRepository passengerRepository;


    @GetMapping("/passengers/{id}")
    public List<Flight> ListByPassengerId(@PathVariable Long id)
    {
        List<BookingList> listOfBookingFlightByPassenger = bookingListRepository.findByIdPassenger(id);
        List<Flight> ListOfFlight = new ArrayList<>();
        Long flightId;

        for (BookingList lb:listOfBookingFlightByPassenger) {
            flightId = lb.getIdFlight();
            ListOfFlight.add(flightRepository.getOne(flightId));
        }
        return ListOfFlight;
    }

    @GetMapping("/flights/{id}")
    public List<Passenger> ListByFlightId(@PathVariable Long id)
    {
        List<BookingList> bookingListsForGivenFlight = bookingListRepository.findByIdFlight(id);
        List<Passenger> listOfPassenger = new ArrayList<>();
        Long passengerId;
        for (BookingList lb:bookingListsForGivenFlight) {
            passengerId = lb.getIdPassenger();
            listOfPassenger.add(passengerRepository.getOne(passengerId));
        }
        return listOfPassenger;
    }

    @DeleteMapping("/del/{idFlight}/{idPassenger}")
    public void delete(@PathVariable Long idFlight, @PathVariable Long idPassenger)
    {
        Flight existingFlight = flightRepository.getOne(idFlight);
        if(existingFlight.getNumberOfPassengers() > 0) {
            existingFlight.updateNumberOfPassengersMinus();
            flightRepository.saveAndFlush(existingFlight);
            BookingList BookFlight = bookingListRepository.findByIdFlightAndIdPassenger(idFlight, idPassenger);
            bookingListRepository.deleteById(BookFlight.getId());
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody BookingList bookingList)
    {
        Long idFlight = bookingList.getIdFlight();
        Long idPassenger = bookingList.getIdPassenger();
        Flight existingFlight = flightRepository.getOne(idFlight);
        Integer passengerHaveTicket = bookingListRepository.countAllByIdPassengerAndIdFlight(idPassenger,idFlight);

        if(existingFlight.getNumberOfPassengers() < existingFlight.getNumberOfSeats() && passengerHaveTicket == 0)
        {
            existingFlight.updateNumberOfPassengersPlus();
            flightRepository.saveAndFlush(existingFlight);
            bookingListRepository.saveAndFlush(bookingList);
        }
    }
}
