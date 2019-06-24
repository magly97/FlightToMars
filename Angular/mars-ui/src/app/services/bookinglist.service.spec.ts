import { TestBed } from '@angular/core/testing';

import { BookinglistService } from './bookinglist.service';

describe('BookinglistService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: BookinglistService = TestBed.get(BookinglistService);
    expect(service).toBeTruthy();
  });
});
