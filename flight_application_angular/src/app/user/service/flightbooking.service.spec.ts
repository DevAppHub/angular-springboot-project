import { TestBed } from '@angular/core/testing';

import { FlightbookingService } from './flightbooking.service';

describe('FlightbookingService', () => {
  let service: FlightbookingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FlightbookingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
