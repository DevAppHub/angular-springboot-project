import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminToastComponent } from './admin-toast.component';

describe('AdminToastComponent', () => {
  let component: AdminToastComponent;
  let fixture: ComponentFixture<AdminToastComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminToastComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminToastComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
