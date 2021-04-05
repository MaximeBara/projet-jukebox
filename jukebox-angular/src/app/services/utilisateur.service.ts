import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Profile } from '../models/user';
import { Jukebox } from '../models/jukebox';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root',
})
export class UtilisateurService {
  readonly route: string = environment.apiUrl + '/api/membre';
  constructor(private http: HttpClient) {}

  getByIdProfil(id: number): Observable<Profile> {
    return this.http.get<Profile>(`${this.route}/${id}`);
  }
  changeByIdMDP(profil: Profile): Observable<Profile> {
    console.log(`${this.route}/${profil.id}`);
    return this.http.put<Profile>(
      `${this.route}/${profil.id}`,
      profil,
      httpOptions
    );
  }

  addFavori(id: number, jukebox: Jukebox): Observable<Profile> {
    console.log("add");
    return this.http.put<Profile>(
      `${this.route}/${id}/addFavori`, jukebox, httpOptions
    );
  }
  removeFavori(id: number, jukebox: Jukebox): Observable<Profile> {
    console.log("remove");
    console.log(id);
    console.log(jukebox);

    return this.http.put<Profile>(
      `${this.route}/${id}/removeFavori`, jukebox, httpOptions
    );
  }

  isFavori(id: number, idJukebox: number): Observable<boolean> {
    return this.http.get<boolean>(
      `${this.route}/${id}/${idJukebox}/isFavori`
    );
  }
}
