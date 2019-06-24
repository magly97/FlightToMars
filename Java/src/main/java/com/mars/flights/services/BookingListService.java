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
import java.util.List;


@Service
public class BookingListService {

    @Autowired
    private BookingListRepository bookingListRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PassengerRepository passengerRepository;

    public List<Flight> ListByPassengerId(Long id)
    {
        List<BookingList> listOfBookingFlightByPassenger = bookingListRepository.findByIdPassenger(id);
        List<Flight> ListOfFlight = new ArrayList<>();
        Long idFlight;

        for (BookingList lb:listOfBookingFlightByPassenger) {
            idFlight = lb.getIdFlight();
            ListOfFlight.add(flightRepository.getOne(idFlight));
        }
        return ListOfFlight;
    }

    public List<Passenger> ListByFlightId(Long id) {
        List<BookingList> bookingListsForGivenFlight = bookingListRepository.findByIdFlight(id);
        List<Passenger> listOfPassenger = new ArrayList<>();
        Long IdPassenger;
        for (BookingList lb:bookingListsForGivenFlight) {
            IdPassenger = lb.getIdPassenger();
            listOfPassenger.add(passengerRepository.getOne(IdPassenger));
        }
        return listOfPassenger;
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
        Integer passengerHasTicket = bookingListRepository.countAllByIdPassengerAndIdFlight(idPassenger,idFlight);

        if(existingFlight.getNumberOfPassengers() < existingFlight.getNumberOfSeats() && passengerHasTicket == 0)
        {
            existingFlight.updateNumberOfPassengersPlus();
            flightRepository.saveAndFlush(existingFlight);
            bookingListRepository.saveAndFlush(bookingList);
        }
    }
}
