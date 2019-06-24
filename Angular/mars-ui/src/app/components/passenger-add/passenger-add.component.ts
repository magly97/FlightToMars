import { Component, OnInit } from '@angular/core';
import { PassengerService } from '../../services/passenger.service';

import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs/Observable';
@Component({
  selector: 'app-passenger-add',
  templateUrl: './passenger-add.component.html',
  styleUrls: ['./passenger-add.component.css']
})
export class PassengerAddComponent implements OnInit {
  countries: string[] = [
    'France',
    'Germany',
    'Poland'
  ];
  passengerform: FormGroup;
  validMessage: string = "";
  constructor(private passengerService: PassengerService) { }

  ngOnInit() {
    this.passengerform = new FormGroup(
      {
        first_name: new FormControl('', Validators.required),
        last_name: new FormControl('', Validators.required),
        gender: new FormControl('', Validators.required),
        birthday: new FormControl('', Validators.required),
        description: new FormControl(),
        country: new FormControl('', Validators.required)


      }
    );
  }
  submitRegistration(){
    if(this.passengerform.valid)
    {
      this.validMessage = "New Passenger created";
      this.passengerService.createNewPassenger(this.passengerform.value).subscribe(
        data => {
          this.passengerform.reset();
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
