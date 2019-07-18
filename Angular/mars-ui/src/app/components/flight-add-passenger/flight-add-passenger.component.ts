import { Component, OnInit } from '@angular/core';
import { FlightService } from '../../services/flight.service';
import { PassengerService } from '../../services/passenger.service';
import { BookinglistService } from '../../services/bookinglist.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-flight-add-passenger',
  templateUrl: './flight-add-passenger.component.html',
  styleUrls: ['./flight-add-passenger.component.css']
})
export class FlightAddPassengerComponent implements OnInit {
  public flight;
  public passengers;
  flightAddPassengerform: FormGroup;
  validMessage: string = "";
  constructor(private flightService: FlightService, private route: ActivatedRoute,
              private router: Router,private passengerService: PassengerService,
              private bookinglistService:BookinglistService) { }

  ngOnInit() {

    this.getPassengers();
    this.flightAddPassengerform = new FormGroup(
      {
        idPassenger: new FormControl('', Validators.required),
      }
    );
    this.getOneFlight(this.route.snapshot.params.id);
  }

  getOneFlight(id:number)
  {
    this.flightService.getOneFlight(id).subscribe(
      data => {
        this.flight = data;
      },
      err => console.error(err),
      () => console.log('Flight loaded')
    );
  }
  getPassengers(){
    this.passengerService.getPassengers().subscribe(
      data => { this.passengers = data },
      err => console.error(err),
      () => console.log('passengers loaded')
    );
  }
  submitAdd(){
    if(this.flightAddPassengerform.valid)
    {
    this.validMessage = "Passenger Added";
     this.bookinglistService.createBooking(this.flight.id, this.flightAddPassengerform.get("idPassenger").value).subscribe(
       data => {
         this.flightAddPassengerform.reset();
         return true
         },
         error => {
           return Observable.throw(error);
         }
     )
   }
   else
   {
     this.validMessage = "Please select passenger";
   }
  }

}
