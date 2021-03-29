import { TestBed } from '@angular/core/testing';

import { MembreGuard } from './membre.guard';

describe('MembreGuard', () => {
  let guard: MembreGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(MembreGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
