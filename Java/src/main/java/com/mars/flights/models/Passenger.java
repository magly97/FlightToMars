package com.mars.flights.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;


@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter @Setter
    private String firstName;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String gender;
    @Getter @Setter
    private Date birthday;
    @Getter @Setter
    private String description;
    @Getter @Setter
    private String country;

    @ManyToMany(mappedBy = "passengerSet", cascade = {CascadeType.MERGE})
    @Getter @Setter
    @JsonIgnoreProperties("passengerSet")
    private Set<Flight> flightSet;

}
