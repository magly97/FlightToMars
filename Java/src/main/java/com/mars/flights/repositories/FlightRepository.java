package com.mars.flights.repositories;
import com.mars.flights.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

}
