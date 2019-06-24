import { Component, OnInit } from '@angular/core';
import { FlightService } from '../../services/flight.service';
@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent implements OnInit {
  public flights;
  constructor(private flightService: FlightService) { }

  ngOnInit() {
    this.getFlights();
  }

  getFlights(){
    this.flightService.getFlights().subscribe(
      data => { this.flights = data },
      err => console.error(err),
      () => console.log('flights loaded')
    );
  }
}
