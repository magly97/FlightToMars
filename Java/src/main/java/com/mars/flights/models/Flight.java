package com.mars.flights.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","flightSet"})
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter @Setter
    private LocalDateTime departure;
    @Getter @Setter
    private LocalDateTime arrival;
    @Getter @Setter
    private Integer price;
    @Getter @Setter
    private Integer numberOfSeats;
    @Getter @Setter
    private Integer numberOfPassengers=0;
    @Getter @Setter
    private String departureFrom;
    @Getter @Setter
    private String arrivalTo;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @Getter @Setter
    @JsonIgnoreProperties("flightSet")
    private Set<Passenger> passengerSet;

    public void decreaseNumberOfPassengers() {
        this.numberOfPassengers -= 1;
    }


}
