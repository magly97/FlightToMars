import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookinglistService } from '../../services/bookinglist.service';
import { PassengerService } from '../../services/passenger.service';


@Component({
  selector: 'app-passanger-show-booking-flight',
  templateUrl: './passanger-show-booking-flight.component.html',
  styleUrls: ['./passanger-show-booking-flight.component.css']
})
export class PassangerShowBookingFlightComponent implements OnInit {
  public passenger;
  public flights;

  constructor(private bookinglistService: BookinglistService,
              private route: ActivatedRoute,private passengerService: PassengerService) { }

  ngOnInit() {
    this.getBookedFlight(this.route.snapshot.params.id),
    this.getOnePassenger(this.route.snapshot.params.id)
  }

  getBookedFlight(id:number)
  {
    this.bookinglistService.getBookedFlight(id).subscribe(
      data => { this.flights = data },
      err => console.error(err),
      () => console.log('Flights loaded')
    );
  }
  getOnePassenger(id:number)
  {
    this.passengerService.getOnePassenger(id).subscribe(
      data => {
        this.passenger = data;
      },
      err => console.error(err),
      () => console.log('Passenger loaded')
    );
  }
  deleteBook(idFlight:number)
  {
    this.bookinglistService.deleteBooking(idFlight,this.passenger.id).subscribe()
    window.location.reload();
  }
}
