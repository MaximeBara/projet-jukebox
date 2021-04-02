import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Playlist } from '../models/playlist';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  readonly route: string = environment.apiUrl + '/api/playlist';

  constructor(private http: HttpClient) { }

  createPlaylist(playlist: Playlist): Observable<Playlist>{
    return this.http.post<Playlist>(`${this.route}`, playlist, httpOptions);
  }

  importFromYoutube(playlist: Playlist): Observable<Playlist>{
    return this.http.post<Playlist>(`${this.route}/importFromYoutube`, playlist);
  }

  updateFromYoutube(playlist: Playlist): Observable<Playlist>{
    return this.http.put<Playlist>(`${this.route}/updateFromYoutube`, playlist);
  }

  getPlaylistById(id: number) : Observable<Playlist>{
    return this.http.get<Playlist>(`${this.route}/${id}`);
  }

  getAllPlaylists(): Observable<Playlist[]> {
    return this.http.get<Playlist[]>(`${this.route}`);
  }

  getAllPlaylistsByOwner(id: number): Observable<Playlist[]> {
    return this.http.get<Playlist[]>(`${this.route}/${id}/findAllByMembre`);
  }

  updatePlaylist(playlist: Partial<Playlist>): Observable<Playlist> {
    return this.http.put<Playlist>(`${this.route}/${playlist.id}`, playlist);
  }

  deleteById(id: number){
    return this.http.delete(`${this.route}/${id}`);
  }

}