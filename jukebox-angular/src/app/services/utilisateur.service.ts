import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Profile } from '../models/user';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  readonly route: string = environment.apiUrl + '/api/membre';
  constructor(private http: HttpClient) { }

  getByIdProfil(id: number): Observable<Profile>{
    return this.http.get<Profile>(`${this.route}/${id}`);
  }
 changeByIdMDP(profil: Profile): Observable<Profile>{
   console.log(`${this.route}/${profil.id}`);
  return this.http.put<Profile>(`${this.route}/${profil.id}`, profil, httpOptions);
 }
}