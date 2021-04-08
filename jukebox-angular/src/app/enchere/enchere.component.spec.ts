import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnchereComponent } from './enchere.component';

describe('EnchereComponent', () => {
  let component: EnchereComponent;
  let fixture: ComponentFixture<EnchereComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnchereComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnchereComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
