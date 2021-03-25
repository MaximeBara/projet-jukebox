import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TitrePageComponent } from './titre-page.component';

describe('TitrePageComponent', () => {
  let component: TitrePageComponent;
  let fixture: ComponentFixture<TitrePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TitrePageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TitrePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
