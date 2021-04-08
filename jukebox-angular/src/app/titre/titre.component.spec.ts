import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TitreComponent } from './titre.component';

describe('TitreComponent', () => {
  let component: TitresComponent;
  let fixture: ComponentFixture<TitresComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TitresComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TitresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
