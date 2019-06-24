import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class BookinglistService {

  constructor(private http: HttpClient) { }

  createBooking(booking) {
    let body = JSON.stringify(booking);
    return this.http.post('/server/api/v1/booking', body, httpOptions);
  }

  deleteBooking(idFlight: number, idPassenger :number) {
    return this.http.delete('/server/api/v1/booking/del/' + idFlight +'/' + idPassenger);
  }

  getPassengersOfFlight(id: number)
  {
    return this.http.get('/server/api/v1/booking/flights/' + id);
  }
  getBookedFlight(id: number)
  {
    return this.http.get('/server/api/v1/booking/passengers/' + id);
  }
}
