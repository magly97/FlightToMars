import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightShowListOfPassengerComponent } from './flight-show-list-of-passenger.component';

describe('FlightShowListOfPassengerComponent', () => {
  let component: FlightShowListOfPassengerComponent;
  let fixture: ComponentFixture<FlightShowListOfPassengerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlightShowListOfPassengerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightShowListOfPassengerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
