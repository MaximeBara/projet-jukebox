import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Enchere } from 'src/app/models/enchere';
import { Jukebox } from 'src/app/models/jukebox';
import { Titre } from 'src/app/models/titre';
import { EnchereService } from 'src/app/services/enchere.service';
import { JukeboxService } from 'src/app/services/jukebox.service';
// import {ConfirmDialogModule} from 'primeng/confirmdialog';
import { ConfirmationService, SelectItem, PrimeNGConfig } from 'primeng/api';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { UtilisateurService } from 'src/app/services/utilisateur.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { Profile } from 'src/app/models/user';

const subject = new Subject<Jukebox>();

@Component({
  selector: 'app-jukebox-page',
  templateUrl: './jukebox-page.component.html',
  styleUrls: ['./jukebox-page.component.css'],
  providers: [ConfirmationService],
})
export class JukeboxPageComponent implements OnInit {
  id!: number;
  userId! : number;
  user!: Profile;

  jukebox: Jukebox = { nom: '', playlist: { nom: '' } } as Jukebox;

  titreEnCours: Titre = { nom: '' } as Titre;

  titres: Titre[] = [];

  titresAJouer: Titre[] = [];

  cols: any[] = [];

  checked: boolean = false;

  @ViewChild('youtube') youtube: any;

  refreshJukebox = new BehaviorSubject<boolean>(true);
  refreshTitres = new BehaviorSubject<boolean>(true);

  idEnchere!: number;
  montantEnchere!: number;

  // @ViewChild('cd') cd: any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private jukeboxApi: JukeboxService,
    private utilisateurApi: UtilisateurService,
    private enchereApi: EnchereService,
    private confirmationService: ConfirmationService,
    private primengConfig: PrimeNGConfig,
    private tokenStorageService: TokenStorageService,
    private utilisateurService: UtilisateurService
  ) {}

  ngAfterContentInit(){
    this.youtube.player.playVideo();

  }

  ngOnInit(): void {
    // this.primengConfig.ripple = true;

    this.activatedRoute.data.subscribe((data) => {
      this.userId = this.tokenStorageService.getUser().id;
      // this.utilisateurService.getByIdProfil(this.userId).subscribe((data) => {
      //   this.user = data;
      // });
    });

    this.activatedRoute.params.subscribe((params) => {
      this.id = parseInt(params.id);
    });

    this.jukeboxApi
      .getByIdWithPlaylistAndTitre(this.id)
      .toPromise()
      .then((jukebox: Jukebox) => {
        if (jukebox.titreEnCours == null) {
          jukebox.titreEnCours = jukebox.playlist.titres[0];
        }
        this.jukebox = jukebox;
        this.titreEnCours = jukebox.titreEnCours;
        this.titresAJouer = jukebox.playlist.titres;
        this.titresAJouer = this.titresAJouer.filter((titre: Titre) => {
          return titre.id !== this.titreEnCours.id;
        });
      });

    // this.jukeboxApi.getByIdWithPlaylistAndTitre2(this.id);

    // this.jukeboxApi.jukeboxObservable$.subscribe((jukebox: Jukebox) => {
    //   this.jukebox = jukebox;

    //   if (jukebox.titreEnCours == null) {
    //     jukebox.titreEnCours = jukebox.playlist.titres[0];
    //   }

    //   this.titreEnCours = jukebox.titreEnCours;
    //   this.titresAJouer = jukebox.playlist.titres;
    //   this.titresAJouer = this.titresAJouer.filter((titre: Titre) => {
    //     return titre.id !== this.titreEnCours.id;
    //   });

    //   console.log(jukebox);
    //   console.log(this.jukebox);
    // });

    this.utilisateurApi
    .isFavori(this.userId, this.id)
    .subscribe((b: boolean) => {
      this.checked = b;
    });

    this.cols = [
      { header: 'ID' },
      { header: 'Artiste' },
      { header: 'Lien' },
      { header: 'Nom' },
    ];

    // this.youtube.player.playVideo();
  }

  accept() {
    console.log('ok');
  }

  // nextTitre2(event: string) {
  //   this.jukeboxApi
  //     .getByIdAllTitreOrderByEnchereSwapEnCours(this.id)
  //     .toPromise()
  //     .then();

  //   this.jukeboxApi.getByIdWithPlaylistAndTitre2(this.id);

  //   this.jukeboxApi.test(this.id);

  //   this.jukeboxApi.jukeboxObservable$.subscribe((jukebox: Jukebox) => {
  //     this.jukebox = jukebox;

  //     if (jukebox.titreEnCours == null) {
  //       jukebox.titreEnCours = jukebox.playlist.titres[0];
  //     }

  //     this.titreEnCours = jukebox.titreEnCours;
  //     this.titresAJouer = jukebox.playlist.titres;
  //     this.titresAJouer = this.titresAJouer.filter((titre: Titre) => {
  //       return titre.id !== this.titreEnCours.id;
  //     });

  //     this.youtube.videoId = this.titreEnCours.lien;
  //     this.youtube.player.playVideo();

  //     subject.next(this.jukebox);
  //   });
  // }

  nextTitre(event: string) {
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

        if (this.titresAJouer.length == 0)
          this.titresAJouer = this.jukebox.playlist.titres;
      });
  }

  encherir(myTitre: Titre) {
    this.confirmationService.confirm({
      message: `Combien voulez-vous enchérir sur le titre ${myTitre.nom} ?`,
      accept: () => {
        console.log(myTitre);
        console.log(this.montantEnchere);

        console.log(this.titreEnCours);

        console.log(this.youtube.videoId);

        const enchere: Enchere = {
          id: 0,
          dateTime: new Date(),
          valeur: this.montantEnchere,
          terminee: false,
          user: this.jukebox.administrateur,
          jukebox: this.jukebox,
          titre: myTitre,
          type: 'P',
        };

        this.enchereApi.postEnchere(enchere).subscribe();
      },
    });
  }

  toggleChange() {
    console.log('ok');
    console.log(this.checked);

    // let isFavori = true;

    // this.utilisateurApi
    //   .isFavori(this.userId, this.id)
    //   .subscribe((b: boolean) => {
    //     isFavori = b;
    //   });

    this.checked
      ? this.utilisateurApi.addFavori(this.userId, this.jukebox).subscribe()
      : this.utilisateurApi.removeFavori(this.userId, this.jukebox).subscribe();

    // this.checked = !this.checked;
  }
}
