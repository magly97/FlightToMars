import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewPassengerComponent } from './view-passenger.component';

describe('ViewPassengerComponent', () => {
  let component: ViewPassengerComponent;
  let fixture: ComponentFixture<ViewPassengerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewPassengerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewPassengerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
