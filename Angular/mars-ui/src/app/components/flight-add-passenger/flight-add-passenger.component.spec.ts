import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FlightAddPassengerComponent } from './flight-add-passenger.component';

describe('FlightAddPassengerComponent', () => {
  let component: FlightAddPassengerComponent;
  let fixture: ComponentFixture<FlightAddPassengerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FlightAddPassengerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FlightAddPassengerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
