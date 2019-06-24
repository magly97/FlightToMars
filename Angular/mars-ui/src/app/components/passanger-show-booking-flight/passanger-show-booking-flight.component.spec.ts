import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PassangerShowBookingFlightComponent } from './passanger-show-booking-flight.component';

describe('PassangerShowBookingFlightComponent', () => {
  let component: PassangerShowBookingFlightComponent;
  let fixture: ComponentFixture<PassangerShowBookingFlightComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PassangerShowBookingFlightComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PassangerShowBookingFlightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
