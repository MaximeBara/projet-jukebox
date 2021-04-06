import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PlaylistService } from 'src/app/services/playlist.service';
import { TitreService } from 'src/app/services/titre.service';
import { Playlist } from '../../models/playlist';

@Component({
  selector: 'app-create-titre',
  templateUrl: './create-titre.component.html',
  styleUrls: ['./create-titre.component.css']
})
export class CreateTitreComponent implements OnInit {

  form: any = {
    lien: ''
  }
  isSuccessful = false;
  isCreateFailed = false;
  errorMessage = '';
  attente = false;
  playlistId = 0;
  currentPlaylist: Partial<Playlist> = {};

  constructor(private titreService: TitreService, private router: Router,
    private activatedRoute: ActivatedRoute, private playlistService: PlaylistService) { }

  ngOnInit(): void {
    const id: number = this.activatedRoute.snapshot.params.id;
    if (id) {
      this.playlistId = id;
      this.currentPlaylist = window.history.state.playlist;
      if (!this.currentPlaylist) {
        this.playlistService.getPlaylistById(this.playlistId).subscribe(
          data => {
            this.currentPlaylist = data;
          }
        )
      }
    } else {
      this.playlistId = 0;
    }
  }

  youtube_parser(url: string): string {
    var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#&?]*).*/;
    var match = url.match(regExp);
    return (match && match[7].length == 11) ? match[7] : "false";
  }

  onSubmit(): void {
    const lien = this.youtube_parser(this.form.lien);
    if (this.playlistId == 0) { // Création d'un titre classique
      this.titreService.importFromYoutube(lien).subscribe(
        data => {
          this.isSuccessful = true;
          this.isCreateFailed = false;
          setTimeout(() => {
            this.router.navigate(['/titres']);
          }, 1000);
          this.attente = false;
        },
        err => {
          this.isCreateFailed = true;
          this.attente = false;
        }
      );
    } else { // Création d'un titre + ajout à la playlist en question
      this.titreService.importFromYoutube(lien).subscribe(
        data => { // Mise à jour de la playlist en question
          this.currentPlaylist.titres?.push(data);
          this.playlistService.updatePlaylist(this.currentPlaylist).subscribe(
            data => {
              this.isSuccessful = true;
              this.isCreateFailed = false;
              setTimeout(() => {
                this.router.navigate(['/playlists/' + this.currentPlaylist.id]);
              }, 1000);
              this.attente = false;
            },
            err => {
              this.isCreateFailed = true;
              this.attente = false;
            }
          )
        },
        err => {
          this.isCreateFailed = true;
          this.attente = false;
        }
      );
    }
  }

}