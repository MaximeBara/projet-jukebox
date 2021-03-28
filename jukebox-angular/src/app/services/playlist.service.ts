import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Playlist } from '../models/playlist';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  readonly route: string = environment.apiUrl + '/api/playlist';

  constructor(private http: HttpClient) { }

  getAllPlaylists(): Observable<Playlist[]> {
    return this.http.get<Playlist[]>(`${this.route}`);
  }

}
