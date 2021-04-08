import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTitreComponent } from './create-titre.component';

describe('CreateTitreComponent', () => {
  let component: CreateTitreComponent;
  let fixture: ComponentFixture<CreateTitreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateTitreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTitreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
