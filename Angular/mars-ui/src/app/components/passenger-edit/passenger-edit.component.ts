import { Component, OnInit } from '@angular/core';
import { PassengerService } from '../../services/passenger.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-passenger-edit',
  templateUrl: './passenger-edit.component.html',
  styleUrls: ['./passenger-edit.component.css']
})
export class PassengerEditComponent implements OnInit {
  countries: string[] = [
    'Poland',
    'France',
    'Germany',
    'USA'
  ];
  public passenger;
  passengerformEdit: FormGroup;
  validMessage: string = "";
  constructor(private passengerService: PassengerService, private route: ActivatedRoute,private router: Router) { }

  ngOnInit() {

    this.passengerformEdit = new FormGroup(
      {
        id: new FormControl(),
        firstName: new FormControl('', Validators.required),
        lastName: new FormControl('', Validators.required),
        gender: new FormControl('', Validators.required),
        birthday: new FormControl('', Validators.required),
        description: new FormControl(),
        country: new FormControl('', Validators.required)

      }
    )
    this.getOnePassenger(this.route.snapshot.params.id);
  }
  submitEdit(){
    if(this.passengerformEdit.valid)
    {
      this.validMessage = "New Passenger created";
      this.passengerService.editPassenger(this.passenger.id, this.passengerformEdit.value).subscribe()
      this.router.navigateByUrl('/passengers/view/'+this.passenger.id);
    }
    else
    {
      this.validMessage = "Please fill out the form";
    }
  }
  getOnePassenger(id:number)
  {
    this.passengerService.getOnePassenger(id).subscribe(
      data => {
        this.passenger = data;
        this.passengerformEdit.setValue(data);

      },
      err => console.error(err),
      () => console.log('Passenger loaded')
    );
  }
}
