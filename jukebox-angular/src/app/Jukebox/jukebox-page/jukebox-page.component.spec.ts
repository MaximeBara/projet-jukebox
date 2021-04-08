import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JukeboxPageComponent } from './jukebox-page.component';

describe('JukeboxPageComponent', () => {
  let component: JukeboxPageComponent;
  let fixture: ComponentFixture<JukeboxPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JukeboxPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JukeboxPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
