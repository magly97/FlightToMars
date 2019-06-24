import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { PassengersListComponent } from './components/passengers-list/passengers-list.component';
import { PassengerAddComponent } from './components/passenger-add/passenger-add.component';
import { PassengerEditComponent } from './components/passenger-edit/passenger-edit.component';
import { PassangerAddFlightComponent } from './components/passanger-add-flight/passanger-add-flight.component';
import { ViewPassengerComponent } from './components/view-passenger/view-passenger.component';
import { PassangerShowBookingFlightComponent } from './components/passanger-show-booking-flight/passanger-show-booking-flight.component';

import { FlightAddComponent } from './components/flight-add/flight-add.component';
import { FlightListComponent } from './components/flight-list/flight-list.component';
import { FlightViewComponent } from './components/flight-view/flight-view.component';
import { FlightEditComponent } from './components/flight-edit/flight-edit.component';
import { FlightAddPassengerComponent } from './components/flight-add-passenger/flight-add-passenger.component';
import { FlightShowListOfPassengerComponent } from './components/flight-show-list-of-passenger/flight-show-list-of-passenger.component';
import { HomeComponent } from './components/home/home.component';





const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'passengers/edit/:id',
    component: PassengerEditComponent
  },
  {
    path: 'passengers/addflight/:id',
    component: PassangerAddFlightComponent
  },
  {
  path: 'passengers/add',
  component: PassengerAddComponent
  },
  {
  path: 'passengers/flights/:id',
  component: PassangerShowBookingFlightComponent
  },
  {
    path: 'passengers/view/:id',
    component: ViewPassengerComponent
  },
  {
  path: 'passengers',
  component: PassengersListComponent
  },
  {
    path: 'flights/addpass/:id',
    component: FlightAddPassengerComponent
  },
  {
    path: 'flights/passengers/:id',
    component: FlightShowListOfPassengerComponent
  },
  {
  path: 'flights/add',
  component: FlightAddComponent
  },
  {
    path: 'flights/view/:id',
    component: FlightViewComponent
  },
  {
    path: 'flights/edit/:id',
    component: FlightEditComponent
  },
  {
  path: 'flights',
  component: FlightListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
