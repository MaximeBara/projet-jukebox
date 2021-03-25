import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Titre } from '../models/titre';

@Injectable({
  providedIn: 'root'
})
export class TitreService {

  readonly route: string = environment.apiUrl + '/api/titre';

  constructor(private http: HttpClient) { }

  getAllTitre(id: number): Observable<Titre>{
    return this.http.get<Titre>(`${this.route}`);
  }

}
