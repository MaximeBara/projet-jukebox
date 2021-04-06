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

  // refreshJukebox = new BehaviorSubject<boolean>(true);
  // refreshTitres = new BehaviorSubject<boolean>(true);

  idEnchere!: number;
  montantEnchere!: number;


  constructor(
    private activatedRoute: ActivatedRoute,
    private jukeboxApi: JukeboxService,
    private utilisateurApi: UtilisateurService,
    private enchereApi: EnchereService,
    private confirmationService: ConfirmationService,
    private tokenStorageService: TokenStorageService,
  ) {}

  ngAfterContentInit(){
    setTimeout(this.youtube.player.playVideo(), 5000);
    // this.youtube.player.playVideo();
  }

  ngAfterContentChecked(){
    // this.youtube.player.playVideo();
  }

  ngOnInit(): void {

    this.activatedRoute.params.subscribe((params) => {
      this.userId = this.tokenStorageService.getUser().id;
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

    this.utilisateurApi
    .isFavori(this.userId, this.id)
    .subscribe((b: boolean) => {
      this.checked = b;
    });

    this.cols = [
      { header: 'Nom' },
      { header: 'Artiste' },
      { header: '' },
      
    ];

    // this.youtube.player.playVideo();
  }

  accept() {
    console.log('ok');
  }


  nextTitre(event: string) {
    this.jukeboxApi
      .getNextTitre(this.id)
      .toPromise()
      .then((titre: Titre) => {
        if (titre === null) titre = this.titresAJouer[0];
        this.titreEnCours = titre;
        this.titresAJouer = this.titresAJouer.filter(
          (titre: Titre) => titre.id !== this.titreEnCours.id
        );
        this.jukeboxApi
          .setTerminee(this.jukebox.id, this.titreEnCours.id)
          .subscribe();
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
    this.checked
      ? this.utilisateurApi.addFavori(this.userId, this.jukebox).subscribe()
      : this.utilisateurApi.removeFavori(this.userId, this.jukebox).subscribe();
  }
}
