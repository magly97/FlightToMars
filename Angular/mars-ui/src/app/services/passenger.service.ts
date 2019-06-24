import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PassengerService {

  constructor(private http: HttpClient) { }

  getPassengers() {
    return this.http.get('/server/api/v1/passengers');
  }

  getOnePassenger(id: number) {
    return this.http.get('/server/api/v1/passengers/' + id);
  }

  createNewPassenger(passenger) {
    let body = JSON.stringify(passenger);
    return this.http.post('/server/api/v1/passengers', body, httpOptions);
  }

  deletePassenger(id: number) {
    return this.http.delete('/server/api/v1/passengers/' + id);
  }

  editPassenger(id:number, passenger) {
    let body = JSON.stringify(passenger);
    return this.http.put('/server/api/v1/passengers/'+id, body, httpOptions);
  }
}
