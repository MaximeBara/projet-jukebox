import { TestBed } from '@angular/core/testing';

import { JukeboxService } from './jukebox.service';

describe('JukeboxService', () => {
  let service: JukeboxService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JukeboxService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
