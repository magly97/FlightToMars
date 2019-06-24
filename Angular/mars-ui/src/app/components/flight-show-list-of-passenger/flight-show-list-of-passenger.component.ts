import { Component, OnInit } from '@angular/core';
import { BookinglistService } from '../../services/bookinglist.service';
import { ActivatedRoute } from '@angular/router';
import { FlightService } from '../../services/flight.service';

@Component({
  selector: 'app-flight-show-list-of-passenger',
  templateUrl: './flight-show-list-of-passenger.component.html',
  styleUrls: ['./flight-show-list-of-passenger.component.css']
})
export class FlightShowListOfPassengerComponent implements OnInit {
public passengers;
public flight;
  constructor(private bookinglistService: BookinglistService,
              private route: ActivatedRoute, private flightService: FlightService) { }

  ngOnInit() {
    this.getPassengersOfFlight(this.route.snapshot.params.id),
    this.getInfoAboutFlight(this.route.snapshot.params.id)
  }

  getPassengersOfFlight(id:number)
  {
    this.bookinglistService.getPassengersOfFlight(id).subscribe(
      data => { this.passengers = data },
      err => console.error(err),
      () => console.log('passengers loaded')
    );
  }
  getInfoAboutFlight(id:number)
  {
    this.flightService.getOneFlight(id).subscribe(
      data => { this.flight = data },
      err => console.error(err),
      () => console.log('passengers loaded')
    );
  }
  deleteBook(idPassenger:number)
  {
    this.bookinglistService.deleteBooking(this.flight.id,idPassenger).subscribe()
    window.location.reload();
  }
}
