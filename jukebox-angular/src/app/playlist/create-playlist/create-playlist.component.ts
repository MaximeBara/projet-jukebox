import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Profile } from 'src/app/models/user';
import { PlaylistService } from 'src/app/services/playlist.service';
import { UtilisateurService } from 'src/app/services/utilisateur.service';
import { Playlist } from '../../models/playlist';
import { TokenStorageService } from '../../services/token-storage.service';


@Component({
  selector: 'app-create-playlist',
  templateUrl: './create-playlist.component.html',
  styleUrls: ['./create-playlist.component.css']
})
export class CreatePlaylistComponent implements OnInit {

  form: any = {
    nom: null
  }
  isSuccessful = false;
  isCreateFailed = false;
  errorMessage = '';
  userId = 0;
  user !: Profile;
  youtube = false;
  attente = false;

  constructor(private playlistService: PlaylistService, private tokenStorageService: TokenStorageService,
    private utilisateurService: UtilisateurService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.userId = this.tokenStorageService.getUser().id;
      this.utilisateurService.getByIdProfil(this.userId).subscribe(data => {
        this.user = data;
      });
      switch (data.kind) {
        case "importFromYoutube":
          this.youtube = true;
          break;
        default:
          console.log("CREATE CLASSIQUE");
      }
    });
  }

  onSubmit(): void {
    const playlist: Playlist = {
      id: 0,
      nom: this.form.nom,
      dateCreation: new Date(),
      lien: this.form.lien,
      createur: this.user,
      titres: [],
      jukeboxes: []
    };
    if(!this.youtube){
      this.playlistService.createPlaylist(playlist).subscribe(
        data => {
          this.isSuccessful = true;
          this.isCreateFailed = false;
          setTimeout(() => {
            this.router.navigate(['/playlists']);
          }, 2000);
        },
        err => {
          this.errorMessage = err.error.message;
          this.isCreateFailed = true;
        }
      )
    }else{
      this.attente = true;
      this.playlistService.createPlaylistByLien(playlist).subscribe(
        data => {
          this.isSuccessful = true;
          this.isCreateFailed = false;
          setTimeout(() => {
            this.router.navigate(['/playlists']);
          }, 2000);
          this.attente = false;
        },
        err => {
          this.errorMessage = err.error.message;
          this.isCreateFailed = true;
          this.attente = false;
        }
      )
    }
  }

}
