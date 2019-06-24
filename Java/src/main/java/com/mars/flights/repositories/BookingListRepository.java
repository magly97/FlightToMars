package com.mars.flights.repositories;

import com.mars.flights.models.BookingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface BookingListRepository extends JpaRepository<BookingList,Long> {

    Integer countAllByIdPassengerAndIdFlight(Long idPassenger, Long idFlight );
    BookingList findByIdFlightAndIdPassenger(Long idFlight, Long idPassenger);
    List<BookingList> findByIdPassenger(Long idPassenger);
    List<BookingList> findByIdFlight(Long idFlight);


}
