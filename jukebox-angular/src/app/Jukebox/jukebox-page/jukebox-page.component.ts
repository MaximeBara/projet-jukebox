import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Jukebox } from 'src/app/models/jukebox';
import { Titre } from 'src/app/models/titre';
import { JukeboxService } from 'src/app/services/jukebox.service';

@Component({
  selector: 'app-jukebox-page',
  templateUrl: './jukebox-page.component.html',
  styleUrls: ['./jukebox-page.component.css'],
})
export class JukeboxPageComponent implements OnInit {
  id!: number;

  jukebox!: Jukebox;

  titreEnCours!: Titre;

  titres: Titre[] = [];

  titresAJouer: Titre[] = [];

  cols: any[] = [];

  @ViewChild('youtube') youtube: any;

  refreshJukebox = new BehaviorSubject<boolean>(true);
  refreshTitres = new BehaviorSubject<boolean>(true);

  constructor(
    private activatedRoute: ActivatedRoute,
    private jukeboxApi: JukeboxService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      this.id = parseInt(params.id);

      this.jukeboxApi
        .getByIdWithPlaylistAndTitre(this.id)
        .toPromise()
        .then((jukebox: Jukebox) => {
          this.jukebox = jukebox;
          this.titreEnCours = jukebox.titreEnCours;
          this.titresAJouer = jukebox.playlist.titres;
          this.titresAJouer = this.titresAJouer.filter(
            (titre: Titre) => titre.id !== this.titreEnCours.id
          );
        });
    });

    // this.refreshJukebox
    //   .pipe(
    //     switchMap((_) => this.jukeboxApi.getByIdWithPlaylistAndTitre(this.id))
    //   )
    //   .subscribe((jukebox: Jukebox) => {
    //     this.jukebox = jukebox;
    //     console.log(jukebox);
    //   });

    // this.refreshTitres
    //   .pipe(
    //     switchMap((_) =>
    //       this.jukeboxApi.getByIdAllTitreOrderByEnchereSwapEnCours(this.id)
    //     )
    //   )
    //   .subscribe((titres: Titre[]) => {
    //     this.titres = titres;
    //     console.log(titres);
    //   });

    // this.jukeboxApi.getNextTitre(this.id).subscribe((titre: Titre) => {
    //   console.log(this.titreEnCours);
    //   this.titreEnCours = titre;
    //   console.log(titre);
    // });

    this.cols = [
      { header: 'ID' },
      { header: 'Artiste' },
      { header: 'Lien' },
      { header: 'Nom' },
    ];
  }

  nextTitre(event: string) {
    // this.jukeboxApi
    //   .getByIdAllTitreOrderByEnchereSwapEnCours(this.id)
    //   .toPromise()
    //   .then((titres: Titre[]) => {
    //     console.log(this.titreEnCours);
    //     this.titreEnCours = titres[0];
    //     console.log(titres);
    //     // this.refreshTitres.next(false);
    //     // this.refreshJukebox.next(false);

    //     this.titresAJouer.filter((titre: Titre) => titre.id !== this.titreEnCours.id);

    //     this.jukeboxApi
    //       .setTerminee(this.jukebox.id, this.titreEnCours.id)
    //       .subscribe(
    //       );
    //     // console.log(this.jukebox.id + '' + this.titreEnCours.id);

    //     this.youtube.videoId = titres[0].lien;
    //     this.youtube.player.playVideo();
    //   });

    this.jukeboxApi
      .getNextTitre(this.id)
      .toPromise()
      .then((titre: Titre) => {
        if (titre === null) titre = this.titresAJouer[0];

        console.log(this.titreEnCours);
        this.titreEnCours = titre;
        console.log(titre);
        // this.refreshTitres.next(false);
        // this.refreshJukebox.next(false);

        this.titresAJouer = this.titresAJouer.filter(
          (titre: Titre) => titre.id !== this.titreEnCours.id
        );

        // console.log(this.titresAJouer);

        this.jukeboxApi
          .setTerminee(this.jukebox.id, this.titreEnCours.id)
          .subscribe();
        // console.log(this.jukebox.id + '' + this.titreEnCours.id);

        this.youtube.videoId = titre.lien;
        this.youtube.player.playVideo();

        if(this.titresAJouer.length == 0) this.titresAJouer = this.jukebox.playlist.titres;
      });
  }
}
