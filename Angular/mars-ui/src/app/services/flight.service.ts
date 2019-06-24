import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  constructor(private http: HttpClient) { }

  getFlights() {
    return this.http.get('/server/api/v1/flights');
  }

  getOneFlight(id: number) {
    return this.http.get('/server/api/v1/flights/' + id);
  }

  createNewFlight(flight) {
    let body = JSON.stringify(flight);
    return this.http.post('/server/api/v1/flights', body, httpOptions);
  }

  deleteFlight(id: number) {
    return this.http.delete('/server/api/v1/flights/' + id);
  }
  edit(id:number, flight) {
    let body = JSON.stringify(flight);
    return this.http.put('/server/api/v1/flights/'+id, body, httpOptions);
  }
}
