import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JukeboxesPageComponent } from './jukeboxes-page.component';

describe('JukeboxesPageComponent', () => {
  let component: JukeboxesPageComponent;
  let fixture: ComponentFixture<JukeboxesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JukeboxesPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JukeboxesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
