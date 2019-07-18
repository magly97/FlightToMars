import { Component, OnInit } from '@angular/core';
import { FlightService } from '../../services/flight.service';
import { PassengerService } from '../../services/passenger.service';
import { BookinglistService } from '../../services/bookinglist.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-passanger-add-flight',
  templateUrl: './passanger-add-flight.component.html',
  styleUrls: ['./passanger-add-flight.component.css']
})
export class PassangerAddFlightComponent implements OnInit {
  public flights;
  public passenger;
  passengerAddFlightrform: FormGroup;
  validMessage: string = "";
  constructor(private flightService: FlightService, private route: ActivatedRoute,
              private router: Router,private passengerService: PassengerService,
              private bookinglistService:BookinglistService) { }

  ngOnInit() {
    this.getOnePassenger(this.route.snapshot.params.id);
    this.getFlights();
    this.passengerAddFlightrform = new FormGroup(
      {
        idFlight: new FormControl('', Validators.required)
      }
    );
  }

  getFlights()
  {
    this.flightService.getFlights().subscribe(
      data => {
        this.flights = data;
      },
      err => console.error(err),
      () => console.log('Flight loaded')
    );
  }
  getOnePassenger(id:number){
    this.passengerService.getOnePassenger(id).subscribe(
      data => { this.passenger = data },
      err => console.error(err),
      () => console.log('passenger loaded')
    );
  }

submitAdd(){
    if(this.passengerAddFlightrform.valid)
    {
    this.validMessage = "Passenger added to flight";
     this.bookinglistService.createBooking(this.passengerAddFlightrform.get("idFlight").value, this.passenger.id).subscribe(
       data => {
         this.passengerAddFlightrform.reset();
         return true
         },
         error => {
           return Observable.throw(error);
         }
     )
   }
   else
   {
     this.validMessage = "Please select flight";
   }
  }

}
