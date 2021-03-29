import { Component, OnInit } from '@angular/core';
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
  userId= 0;
  user !: Profile;

  constructor(private playlistService: PlaylistService, private tokenStorageService: TokenStorageService, private utilisateurService: UtilisateurService) { }

  ngOnInit(): void {
    this.userId = this.tokenStorageService.getUser().id;
    this.utilisateurService.getByIdProfil(this.userId).subscribe(data => {
      this.user = data;
    })
  }

  onSubmit(): void {
    const playlist: Playlist = {
      id: 0,
      nom: this.form.nom,
      dateCreation: new Date(),
      lien: "",
      createur: this.user,
      titres: [],
      jukeboxes: []
    };

    this.playlistService.createPlaylist(playlist).subscribe(
      data => {
        this.isSuccessful = true;
        this.isCreateFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isCreateFailed = true;
      }
    )
  }

}
