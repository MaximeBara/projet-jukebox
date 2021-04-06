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
     console.log(3);
    // setTimeout(this.youtube.player.playVideo(), 5000);
    console.log(4);
    // this.youtube.player.playVideo();
  }

  ngAfterContentChecked(){
    // this.youtube.player.playVideo();
  }

  ngOnInit(): void {

    this.activatedRoute.params.subscribe((params) => {
      this.userId = this.tokenStorageService.getUser().id;
      this.utilisateurApi.getByIdProfil(this.userId).subscribe(
        (user: Profile) => {
          console.log(1);
          this.user = user;
        }
      );
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
        console.log(2);
        
        console.log(jukebox.titreEnCours)

        this.youtube.videoId =  jukebox.titreEnCours.lien;
        this.youtube.player.playVideo();
        // setTimeout(this.youtube.player.playVideo(), 5000);
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


  changeSize(){
    console.log("ok")

    if(this.youtube.fs){
      this.youtube.height = 720;
      this.youtube.width = 1280;
      this.youtube.fs = false;
    } else {
      this.youtube.height = 1000;
      this.youtube.width = 1800;
      this.youtube.fs = true;
    }
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

console.log(this.user);

    this.confirmationService.confirm({
      message: `Combien voulez-vous enchérir sur le titre ${myTitre.nom} ?`,
      accept: () => {
        const enchere: Enchere = {
          id: 0,
          dateTime: new Date(),
          valeur: this.montantEnchere,
          terminee: false,
          membre: this.user,
          jukebox: this.jukebox,
          titre: myTitre,
          type: 'P',
        };

        console.log(enchere)

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
