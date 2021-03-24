import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Jukebox } from '../models/jukebox';

@Injectable({
  providedIn: 'root'
})
export class JukeboxService {

  readonly route: string = environment.apiUrl + '/api/jukebox';

  constructor(private http: HttpClient) { }

  getByIdWithPlaylistAndTitre(id: number): Observable<Jukebox>{
    return this.http.get<Jukebox>(`${this.route}/${id}/withPlaylistAndTitre`);
  }
}
