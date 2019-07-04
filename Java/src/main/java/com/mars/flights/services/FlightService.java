package com.mars.flights.services;

import com.mars.flights.models.BookingList;
import com.mars.flights.models.Flight;
import com.mars.flights.repositories.BookingListRepository;
import com.mars.flights.repositories.FlightRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class FlightService {

    private final FlightRepository flightRepository;
    private final BookingListRepository bookingListRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository, BookingListRepository bookingListRepository) {
        this.flightRepository = flightRepository;
        this.bookingListRepository = bookingListRepository;
    }

    public List<Flight> flightList() {
        return flightRepository.findAll();
    }

    public void createFlight(Flight flight) {
        flightRepository.saveAndFlush(flight);
    }

    public Flight getFlight(Long id) {
        return flightRepository.getOne(id);
    }

    public void deleteFlight(Long id) {
        List<BookingList> bookingList = bookingListRepository.findByIdFlight(id);
        bookingList.forEach(booking -> bookingListRepository.deleteById(booking.getId()));
        flightRepository.deleteById(id);
    }

    public void updateFlight(Long id, Flight flight) {
        Flight existingFlight = flightRepository.getOne(id);
        BeanUtils.copyProperties(flight, existingFlight);
        flightRepository.saveAndFlush(existingFlight);
    }
}
