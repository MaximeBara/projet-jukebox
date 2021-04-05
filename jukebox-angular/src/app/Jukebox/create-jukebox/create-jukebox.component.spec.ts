import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateJukeboxComponent } from './create-jukebox.component';

describe('CreateJukeboxComponent', () => {
  let component: CreateJukeboxComponent;
  let fixture: ComponentFixture<CreateJukeboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateJukeboxComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateJukeboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
