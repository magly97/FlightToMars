import { Component, OnInit } from '@angular/core';
import { FlightService } from '../../services/flight.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-flight-edit',
  templateUrl: './flight-edit.component.html',
  styleUrls: ['./flight-edit.component.css']
})
export class FlightEditComponent implements OnInit {
  fromto: string[] = [
    'Earth',
    'Mars'
  ];
  public flight;
  flightformEdit: FormGroup;
  validMessage: string = "";
  constructor(private flightService: FlightService, private route: ActivatedRoute,private router: Router) { }

  ngOnInit() {

    this.flightformEdit = new FormGroup(
      {
        id: new FormControl(),
        departureFrom: new FormControl(),
        arrivalTo: new FormControl(),
        arrival: new FormControl(),
        departure: new FormControl(),
        numberOfSeats: new FormControl(),
        price: new FormControl(),
        numberOfPassengers: new FormControl()
      }
    )
    this.getOneFlight(this.route.snapshot.params.id);
  }
  submintEdit(){
    if(this.flightformEdit.valid
        && this.flightformEdit.get('departureFrom').value != this.flightformEdit.get('arrivalTo').value
        && this.flightformEdit.get('arrival').value > this.flightformEdit.get('departure').value
        && this.flightformEdit.get('numberOfSeats').value >= this.flightformEdit.get('numberOfPassengers').value)
    {
      this.flightService.edit(this.flight.id, this.flightformEdit.value).subscribe();
      this.router.navigateByUrl('/flights/view/'+this.flight.id);
    }
    else
    {
      this.validMessage = "Please fill form corectly";
    }
  }
  getOneFlight(id:number)
  {
    this.flightService.getOneFlight(id).subscribe(
      data => {
        this.flight = data;
        this.flightformEdit.setValue(data);

      },
      err => console.error(err),
      () => console.log('Flight loaded')
    );
  }
}
