import { Component, OnInit } from '@angular/core';
import { PassengerService } from '../../services/passenger.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
@Component({
  selector: 'app-view-passenger',
  templateUrl: './view-passenger.component.html',
  styleUrls: ['./view-passenger.component.css']
})
export class ViewPassengerComponent implements OnInit {
  public passenger;
  constructor(private passengerService: PassengerService, private route: ActivatedRoute,private router: Router) { }

  ngOnInit() {
    this.getOnePassenger(this.route.snapshot.params.id);
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
  deletePassenger(id: number)
  {
    this.passengerService.deletePassenger(id).subscribe()
    this.router.navigateByUrl('/passengers');
  }
  clickEdit(id: number)
  {
    this.router.navigateByUrl('/passengers/edit/'+id);
  }
  addFlight(id: number)
  {
    this.router.navigateByUrl('/passengers/addflight/'+id);
  }
  showFlights(id: number)
  {
    this.router.navigateByUrl('/passengers/flights/'+id);
  }

}
