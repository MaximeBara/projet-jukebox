import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Enchere } from '../models/enchere';



@Injectable({
  providedIn: 'root'
})
export class EnchereService {

  readonly route: string = environment.apiUrl + '/api/enchere';

  constructor(private http: HttpClient) { }

  getAllEncheresByUser(id: number): Observable<Enchere[]>{
    return this.http.get<Enchere[]>(`${this.route}/findByMembre/${id}`);
  }

  postEnchere(enchere: Enchere): Observable<Enchere>{
    console.log("ici2");
    return this.http.post<Enchere>(`${this.route}`, enchere)
  }
}