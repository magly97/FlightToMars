import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PassangerAddFlightComponent } from './passanger-add-flight.component';

describe('PassangerAddFlightComponent', () => {
  let component: PassangerAddFlightComponent;
  let fixture: ComponentFixture<PassangerAddFlightComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PassangerAddFlightComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PassangerAddFlightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
