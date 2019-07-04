package com.mars.flights.services;

import com.mars.flights.models.BookingList;
import com.mars.flights.models.Flight;
import com.mars.flights.models.Passenger;
import com.mars.flights.repositories.BookingListRepository;
import com.mars.flights.repositories.FlightRepository;
import com.mars.flights.repositories.PassengerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;
    private final BookingListRepository bookingListRepository;
    private final FlightRepository flightRepository;

    @Autowired
    public PassengerService(PassengerRepository passengerRepository, BookingListRepository bookingListRepository,
                            FlightRepository flightRepository) {
        this.passengerRepository = passengerRepository;
        this.bookingListRepository = bookingListRepository;
        this.flightRepository = flightRepository;
    }

    public List<Passenger> passengersList() {
        return passengerRepository.findAll();
    }

    public void createPassenger(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    public void deletePassenger(Long id) {
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

    public Passenger getPassenger(Long id) {
        return passengerRepository.getOne(id);
    }

    public  Passenger updatePassenger(Long id, Passenger passenger) {
        Passenger existingPassenger = passengerRepository.getOne(id);
        BeanUtils.copyProperties(passenger,existingPassenger);
        return passengerRepository.saveAndFlush(existingPassenger);
    }
}
