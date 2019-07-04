package com.mars.flights.services;

import com.mars.flights.models.BookingList;
import com.mars.flights.models.Flight;
import com.mars.flights.models.Passenger;
import com.mars.flights.repositories.BookingListRepository;
import com.mars.flights.repositories.FlightRepository;
import com.mars.flights.repositories.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class BookingListService {

    private final BookingListRepository bookingListRepository;
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;

    @Autowired
    public BookingListService(BookingListRepository bookingListRepository, FlightRepository flightRepository,
                              PassengerRepository passengerRepository) {
        this.bookingListRepository = bookingListRepository;
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }

    public List<Flight> ListByPassengerId(Long id)
    {
        try {
            List<BookingList> listOfBookingFlightByPassenger = bookingListRepository.findByIdPassenger(id);
            List<Flight> listOfFlight = new ArrayList<>();
            listOfBookingFlightByPassenger.forEach(
                    bookingList -> listOfFlight.add(flightRepository.getOne(bookingList.getIdFlight())));
            return listOfFlight;
        }
        catch(Exception e) {
            return Collections.emptyList();
        }
    }

    public List<Passenger> ListByFlightId(Long id) {
        try {
            List<BookingList> bookingListsForGivenFlight = bookingListRepository.findByIdFlight(id);
            List<Passenger> listOfPassenger = new ArrayList<>();
            bookingListsForGivenFlight.forEach(
                    bookingList -> listOfPassenger.add(passengerRepository.getOne(bookingList.getIdPassenger())));
            return listOfPassenger;
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void deleteBookingList(Long idFlight, Long idPassenger) {
        Flight existingFlight = flightRepository.getOne(idFlight);
        if(existingFlight.getNumberOfPassengers() > 0) {
            existingFlight.updateNumberOfPassengersMinus();
            flightRepository.saveAndFlush(existingFlight);
            BookingList BookFlight = bookingListRepository.findByIdFlightAndIdPassenger(idFlight, idPassenger);
            bookingListRepository.deleteById(BookFlight.getId());
        }
    }

    public void createBookingList(BookingList bookingList)
    {
        Long idFlight = bookingList.getIdFlight();
        Long idPassenger = bookingList.getIdPassenger();
        Flight existingFlight = flightRepository.getOne(idFlight);
        Integer passengerHasTicket = bookingListRepository.countAllByIdPassengerAndIdFlight(idPassenger, idFlight);

        if(existingFlight.getNumberOfPassengers() < existingFlight.getNumberOfSeats() && passengerHasTicket == 0) {
            existingFlight.updateNumberOfPassengersPlus();
            flightRepository.saveAndFlush(existingFlight);
            bookingListRepository.saveAndFlush(bookingList);
        }
    }
}
