import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Profile } from '../models/user';

const API_URL = 'http://localhost:8080/api/test/';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  readonly route: string = environment.apiUrl + '/api/membre';
  constructor(private http: HttpClient) { }

  getByIdProfil(id: number): Observable<Profile>{
    return this.http.get<Profile>(`${this.route}/${id}`);
  }
}