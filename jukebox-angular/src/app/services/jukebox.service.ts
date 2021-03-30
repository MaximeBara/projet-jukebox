import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Jukebox } from '../models/jukebox';
import { Titre } from '../models/titre';

@Injectable({
  providedIn: 'root'
})
export class JukeboxService {

  readonly route: string = environment.apiUrl + '/api/jukebox';

  constructor(private http: HttpClient) { }

  getAllJukeboxByAdministrateur(id: number): Observable<Jukebox[]>{
    return this.http.get<Jukebox[]>(`${this.route}/${id}/allByAdministrateur`);
  }

  getByIdWithPlaylistAndTitre(id: number): Observable<Jukebox>{
    return this.http.get<Jukebox>(`${this.route}/${id}/withPlaylistAndTitre`);
  }

  getByIdAllTitreOrderByEnchere(id: number): Observable<Titre[]>{
    return this.http.get<Titre[]>(`${this.route}/${id}/allTitreOrderByEnchere`);
  }

  getByIdAllTitreOrderByEnchereSwapEnCours(id: number): Observable<Titre[]>{
    return this.http.get<Titre[]>(`${this.route}/${id}/allTitreOrderByEnchereSwapEnCours`);
  }

  getNextTitre(id: number): Observable<Titre>{
    return this.http.get<Titre>(`${this.route}/${id}/nextTitre`);
  }

  setTerminee(idJukebox: number, idTitre:number){
    console.log(`${this.route}/${idJukebox}/setTerminee/${idTitre}`);

    return this.http.put(`${this.route}/${idJukebox}/setTerminee/${idTitre}`, true);
  }
}
