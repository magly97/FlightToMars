package com.mars.flights.repositories;
import com.mars.flights.models.Passenger;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
