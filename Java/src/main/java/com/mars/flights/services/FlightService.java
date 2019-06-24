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

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    BookingListRepository bookingListRepository;

    public List<Flight> flightList() {
        return flightRepository.findAll();
    }

    public boolean createFlight(Flight flight) {
        flightRepository.saveAndFlush(flight);
        return true;
    }

    public Flight getFlight(Long id)
    {
        return flightRepository.getOne(id);
    }

    public void deleteFlight(Long id) {
        List<BookingList> Booking = bookingListRepository.findByIdFlight(id);
        for (BookingList bl:Booking) {
            bookingListRepository.deleteById(bl.getId());
        }
        flightRepository.deleteById(id);
    }

    public void updateFlight(Long id, Flight flight) {
        Flight existingFlight = flightRepository.getOne(id);
        BeanUtils.copyProperties(flight, existingFlight);
        flightRepository.saveAndFlush(existingFlight);
    }
}
