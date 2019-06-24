package com.mars.flights.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Table(name = "passenger")
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

}
