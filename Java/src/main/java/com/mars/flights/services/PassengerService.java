package com.mars.flights.services;

import com.mars.flights.models.Flight;
import com.mars.flights.models.Passenger;
import com.mars.flights.repositories.PassengerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;


@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> passengersList() {
        return passengerRepository.findAll();
    }

    public void createPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    public void deletePassenger(Long id) {
        Passenger passenger = passengerRepository.getOne(id);
        Set<Flight> flightsSet = passenger.getFlightSet();
        flightsSet.forEach(Flight::decreaseNumberOfPassengers);
        flightsSet.forEach(flights -> flights.getPassengerSet().remove(passenger));
        passengerRepository.deleteById(id);
    }

    public Passenger getPassenger(Long id) {
        return passengerRepository.getOne(id);
    }

    public  Passenger updatePassenger(Long id, Passenger passenger) {
        Passenger existingPassenger = passengerRepository.getOne(id);
        BeanUtils.copyProperties(passenger,existingPassenger);
        return passengerRepository.saveAndFlush(existingPassenger);
    }

    public Set<Flight> flightsPassenger(Long id){
        Passenger existingPassenger = passengerRepository.getOne(id);
        return existingPassenger.getFlightSet();
    }
}
