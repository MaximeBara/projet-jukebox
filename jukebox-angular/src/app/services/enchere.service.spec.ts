import { TestBed } from '@angular/core/testing';

import { EnchereService } from './enchere.service';

describe('EnchereService', () => {
  let service: EnchereService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnchereService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
