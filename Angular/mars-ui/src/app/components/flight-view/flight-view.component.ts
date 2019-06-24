import { Component, OnInit } from '@angular/core';
import { FlightService } from '../../services/flight.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
@Component({
  selector: 'app-flight-view',
  templateUrl: './flight-view.component.html',
  styleUrls: ['./flight-view.component.css']
})
export class FlightViewComponent implements OnInit {
  public flight;
  constructor(private flightService: FlightService, private route: ActivatedRoute,private router: Router) { }

  ngOnInit() {
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
  deleteFlight(id: number)
  {
    this.flightService.deleteFlight(id).subscribe()
    this.router.navigateByUrl('/flights');
  }
  clickEdit(id: number)
  {
    this.router.navigateByUrl('/flights/edit/'+id);
  }
  addPassenger(id: number)
  {
    this.router.navigateByUrl('/flights/addpass/'+id);
  }
  showPassengerOfThisFlight(id: number)
  {
    this.router.navigateByUrl('/flights/passengers/'+id);
  }
}
