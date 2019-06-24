import { Component, OnInit } from '@angular/core';
import { PassengerService } from '../../services/passenger.service';
@Component({
  selector: 'app-passengers-list',
  templateUrl: './passengers-list.component.html',
  styleUrls: ['./passengers-list.component.css']
})
export class PassengersListComponent implements OnInit {
  public passengers;
  constructor(private passengerService: PassengerService) { }

  ngOnInit() {
    this.getPassengers();
  }

  getPassengers(){
    this.passengerService.getPassengers().subscribe(
      data => { this.passengers = data },
      err => console.error(err),
      () => console.log('passengers loaded')
    );
  }
}
