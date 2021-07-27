import { TestBed } from '@angular/core/testing';

import { AdminToastService } from './admin-toast.service';

describe('AdminToastService', () => {
  let service: AdminToastService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminToastService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
