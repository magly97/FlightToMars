package com.mars.flights.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "bookinglist")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class BookingList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter @Setter
    private Long idPassenger;
    @Getter @Setter
    private Long idFlight;

}
