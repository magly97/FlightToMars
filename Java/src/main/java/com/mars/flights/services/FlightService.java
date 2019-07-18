package com.mars.flights.services;

import com.mars.flights.models.Flight;
import com.mars.flights.models.Passenger;
import com.mars.flights.repositories.FlightRepository;
import com.mars.flights.repositories.PassengerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;


@Service
public class FlightService {

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private FlightRepository flightRepository;

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
        flightRepository.deleteById(id);
    }

    public void updateFlight(Long id, Flight flight) {
        Flight existingFlight = flightRepository.getOne(id);
        BeanUtils.copyProperties(flight, existingFlight);
        flightRepository.saveAndFlush(existingFlight);
    }

    public Set<Passenger> passengersFlight(Long id) {
        Flight existingFlight = flightRepository.getOne(id);
        return existingFlight.getPassengerSet();
    }

    public void deleteBooking(Long passengerId, Long flightId) {
        Flight flight =  flightRepository.getOne(flightId);
        Set<Passenger> passengerSet = flight.getPassengerSet();
        passengerSet.remove(passengerRepository.getOne(passengerId));
        flight.setNumberOfPassengers(passengerSet.size());
        BeanUtils.copyProperties(flightRepository.getOne(flightId), flight);
        flightRepository.saveAndFlush(flight);
    }
    public void createBooking(Long passengerId, Long flightId) {
        Flight flight = flightRepository.getOne(flightId);
        Set<Passenger> passengerSet = flight.getPassengerSet();
        passengerSet.add(passengerRepository.getOne(passengerId));
        flight.setNumberOfPassengers(passengerSet.size());
        BeanUtils.copyProperties(flightRepository.getOne(flightId), flight);
        flightRepository.saveAndFlush(flight);
    }
}
