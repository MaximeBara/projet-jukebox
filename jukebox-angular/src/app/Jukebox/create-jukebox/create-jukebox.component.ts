import { Component, OnInit } from '@angular/core';
import { Playlist } from 'src/app/models/playlist';
import { PlaylistService } from 'src/app/services/playlist.service';
import { DropdownModule } from 'primeng/dropdown';
import { Jukebox } from 'src/app/models/jukebox';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UtilisateurService } from 'src/app/services/utilisateur.service';
import { Profile } from 'src/app/models/user';
import { JukeboxService } from 'src/app/services/jukebox.service';
import { Titre } from 'src/app/models/titre';

@Component({
  selector: 'app-create-jukebox',
  templateUrl: './create-jukebox.component.html',
  styleUrls: ['./create-jukebox.component.css'],
})
export class CreateJukeboxComponent implements OnInit {
  form: any = {
    nom: null,
    playlist: { nom: null },
  };

  isSuccessful = false;
  isCreateFailed = false;
  errorMessage = '';
  attente = false;
  userId = 0;
  user!: Profile;

  selectedCountry: any;
  playlistSelected: any;


  nomJukebox: string = "";
  selectedType: any = 0;
  selectedPlaylist!: Playlist;

  typeJukebox: any;

  playlists!: Playlist[];

  value1!: string;

  constructor(
    private playlistService: PlaylistService,
    private jukeboxService: JukeboxService,
    private tokenStorageService: TokenStorageService,
    private utilisateurService: UtilisateurService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.typeJukebox = [
      { nom: 'Gratuit', code: 0 },
      { nom: 'Payant', code: 1 },
      { nom: 'Mixte', code: 2 },
    ];

    // this.selectedType = this.typeJukebox[0];
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe((data) => {
      this.userId = this.tokenStorageService.getUser().id;
      this.utilisateurService.getByIdProfil(this.userId).subscribe((data) => {
        this.user = data;
      });
    });

    this.playlistService
      .getAllPlaylists()
      .subscribe((playlists: Playlist[]) => {
        this.playlists = playlists;
      });
  }

  // test() {
  //   console.log(this.playlists);
  //   console.log(this.playlistSelected);
  // }

  // consoleLog($value: any){
  //   console.log($value);
  // }

   randomCode(size: number): string {
    var result           = [];
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < size; i++ ) {
      result.push(characters.charAt(Math.floor(Math.random() * 
 charactersLength)));
   }
   return result.join('');
}

  onSubmit(): void {

    const jukebox: Jukebox = {
      id: 0,
      nom: this.nomJukebox,
      code: this.randomCode(12),
      typeEnchere: this.selectedType,
      administrateur: this.user,
      playlist: this.selectedPlaylist,
      connectes: [],
      encheres: [],
      fans: [],
      titreEnCours: {} as Titre,
    };

    this.jukeboxService.createJukebox(jukebox).subscribe(
      (data) => {
        this.isSuccessful = true;
        this.isCreateFailed == false;
        this.router.navigate(['/jukeboxes']);
      },
      (err) => {
        this.errorMessage = err.error.message;
        this.isCreateFailed = true;
      }
    );
  }
}
