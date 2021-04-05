import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Jukebox } from '../models/jukebox';
import { Titre } from '../models/titre';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class JukeboxService {
  readonly route: string = environment.apiUrl + '/api/jukebox';

  private jukeboxSubject = new Subject<Jukebox>();
  jukeboxObservable$ = this.jukeboxSubject.asObservable();

  jukeboxSubject2: Subject<Jukebox>;

  constructor(private http: HttpClient) {this.jukeboxSubject2 = new Subject<Jukebox>()}

  // test2(): void{
  //   this.http
  //     .get<Jukebox>(`${this.route}/41/withPlaylistAndTitre`)
  //     .subscribe((jukebox: Jukebox) => {
  //       this.jukeboxSubject2.next(jukebox);
  //     });
  // }


  // test(id: number): void{
  //   this.http
  //     .get<Jukebox>(`${this.route}/${id}/withPlaylistAndTitre`)
  //     .subscribe((jukebox: Jukebox) => {
  //       this.jukeboxSubject2.next(jukebox);
  //     });
  // }


  // getByIdWithPlaylistAndTitre2(id: number): void {
  //   this.http
  //     .get<Jukebox>(`${this.route}/${id}/withPlaylistAndTitre`)
  //     .toPromise().then((jukebox: Jukebox) => {
  //       this.jukeboxSubject.next(jukebox);
  //     });
  // }

  createJukebox(jukebox: Jukebox): Observable<Jukebox>{
    return this.http.post<Jukebox>(`${this.route}`, jukebox, httpOptions);
  }

  getAllJukebox(): Observable<Jukebox[]> {
    return this.http.get<Jukebox[]>(`${this.route}/`);
  }

  getAllJukeboxByAdministrateur(id: number): Observable<Jukebox[]> {
    return this.http.get<Jukebox[]>(`${this.route}/${id}/allByAdministrateur`);
  }

  getByIdWithPlaylistAndTitre(id: number): Observable<Jukebox> {
    return this.http.get<Jukebox>(`${this.route}/${id}/withPlaylistAndTitre`);
  }

  getByIdAllTitreOrderByEnchere(id: number): Observable<Titre[]> {
    return this.http.get<Titre[]>(`${this.route}/${id}/allTitreOrderByEnchere`);
  }

  getByIdAllTitreOrderByEnchereSwapEnCours(id: number): Observable<Titre[]> {
    return this.http.get<Titre[]>(
      `${this.route}/${id}/allTitreOrderByEnchereSwapEnCours`
    );
  }

  getNextTitre(id: number): Observable<Titre> {
    return this.http.get<Titre>(`${this.route}/${id}/nextTitre`);
  }

  setTerminee(idJukebox: number, idTitre: number) {
    console.log(`${this.route}/${idJukebox}/setTerminee/${idTitre}`);

    return this.http.put(
      `${this.route}/${idJukebox}/setTerminee/${idTitre}`,
      true
    );
  }

  getByCode(code: string): Observable<Jukebox>{
    return this.http.get<Jukebox>(`${this.route}/findByCode/${code}`);
  }
}
