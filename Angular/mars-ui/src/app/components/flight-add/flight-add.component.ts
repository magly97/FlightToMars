import { Component, OnInit } from '@angular/core';
import { FlightService } from '../../services/flight.service';

import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-flight-add',
  templateUrl: './flight-add.component.html',
  styleUrls: ['./flight-add.component.css']
})
export class FlightAddComponent implements OnInit {
  fromto: string[] = [
    'Earth',
    'Mars'
  ];
  flightform: FormGroup;
  validMessage: string = "";

  constructor(private flightService: FlightService) { }

  ngOnInit() {
    this.flightform = new FormGroup(
      {

        departureFrom: new FormControl('', Validators.required),
        arrivalTo: new FormControl('', Validators.required),
        arrival: new FormControl('', Validators.required),
        departure: new FormControl('', Validators.required),
        numberOfSeats: new FormControl('', Validators.required),
        price: new FormControl('', Validators.required)
      }
    );
  }
  submitRegistration(){
    if(this.flightform.valid && this.flightform.get('departureFrom').value != this.flightform.get('arrivalTo').value
       && this.flightform.get('arrival').value > this.flightform.get('departure').value)
    {
      this.validMessage = "New Flight created";
      this.flightService.createNewFlight(this.flightform.value).subscribe(
        data => {
          this.flightform.reset();
          return true
          },
          error => {
            return Observable.throw(error);
          }
      )
    }
    else
    {
      this.validMessage = "Please fill out the form";
    }

  }
}
