import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { PassengerService } from './services/passenger.service';
import { BookinglistService } from './services/bookinglist.service';

import { PassengersListComponent } from './components/passengers-list/passengers-list.component';

import { PassengerAddComponent } from './components/passenger-add/passenger-add.component';
import { ViewPassengerComponent } from './components/view-passenger/view-passenger.component';

import { FlightService } from './services/flight.service';
import { FlightListComponent } from './components/flight-list/flight-list.component';
import { FlightAddComponent } from './components/flight-add/flight-add.component';
import { FlightViewComponent } from './components/flight-view/flight-view.component';
import { FlightEditComponent } from './components/flight-edit/flight-edit.component';
import { PassengerEditComponent } from './components/passenger-edit/passenger-edit.component';
import { FlightAddPassengerComponent } from './components/flight-add-passenger/flight-add-passenger.component';
import { PassangerAddFlightComponent } from './components/passanger-add-flight/passanger-add-flight.component';
import { PassangerShowBookingFlightComponent } from './components/passanger-show-booking-flight/passanger-show-booking-flight.component';
import { FlightShowListOfPassengerComponent } from './components/flight-show-list-of-passenger/flight-show-list-of-passenger.component';
import { HomeComponent } from './components/home/home.component';

@NgModule({
  declarations: [
    AppComponent,
    PassengersListComponent,
    PassengerAddComponent,
    ViewPassengerComponent,
    FlightListComponent,
    FlightAddComponent,
    FlightViewComponent,
    FlightEditComponent,
    PassengerEditComponent,
    FlightAddPassengerComponent,
    PassangerAddFlightComponent,
    PassangerShowBookingFlightComponent,
    FlightShowListOfPassengerComponent,
    HomeComponent,

  ],
  imports: [
    ReactiveFormsModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [PassengerService, FlightService, BookinglistService],
  bootstrap: [AppComponent]
})
export class AppModule { }
