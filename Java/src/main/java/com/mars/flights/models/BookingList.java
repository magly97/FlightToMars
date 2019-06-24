package com.mars.flights.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import javax.persistence.*;


@Entity
@Table(name = "bookinglist")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BookingList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private Long idPassenger;
    @Getter
    private Long idFlight;

}
