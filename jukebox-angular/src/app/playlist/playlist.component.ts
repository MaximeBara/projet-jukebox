import { Component, OnInit } from '@angular/core';
import { ConfirmationService, Message, PrimeNGConfig } from 'primeng/api';
import { PlaylistService } from 'src/app/services/playlist.service';
import { TokenStorageService } from '../services/token-storage.service';
import { Playlist } from '../models/playlist';
import { Titre } from '../models/titre';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css'],
  providers: [ConfirmationService]
})
export class PlaylistComponent implements OnInit {

  playlists: Playlist[] = [];
  titres: Titre[] = [];
  msgs: Message[] = [];
  userId: number = 0;
  editModeMap = new Map();

  constructor(private playlistService: PlaylistService, private confirmationService: ConfirmationService, 
    private primengConfig: PrimeNGConfig, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.playlistService.getAllPlaylists().subscribe((playlists: Playlist[]) => {
      this.playlists = playlists;
    });
    this.userId = this.tokenStorageService.getUser().id;
  }

  onFocusOut(event: any, playlist: Playlist){
    this.editModeMap.set(playlist.id, false);
    playlist.nom = event.target.value;
    this.playlistService.updatePlaylist(playlist).subscribe();
  }

  confirm1(playlist: Playlist) {
    this.confirmationService.confirm({
      message: 'Etes-vous sûr de vouloir supprimer cette playlist ?',
      header: 'Confirmation de suppression de la playlist ' + playlist.nom,
      icon: 'pi pi-info-circle',
      accept: () => {
        this.playlistService.deleteById(playlist.id).subscribe(() => {
          this.playlists = this.playlists.filter(item => item.id != playlist.id);
          this.msgs = [{ severity: 'info', summary: 'Confirmé', detail: "La playlist a bien été supprimée." }];
        });
      },
      reject: () => {
        this.msgs = [{ severity: 'info', summary: 'Rejeté', detail: "La playlist n'a pas été supprimée." }];
      }
    });
  }

  confirm2(playlist: Playlist) {
    this.confirmationService.confirm({
      message: 'Etes-vous sûr de vouloir mettre à jour cette playlist ?',
      header: 'Confirmation de mise à jour de la playlist ' + playlist.nom,
      icon: 'pi pi-info-circle',
      accept: () => {
        this.playlistService.updateFromYoutube(playlist).subscribe(() => {
          this.msgs = [{ severity: 'info', summary: 'Confirmé', detail: "La playlist a bien été mise à jour." }];
        });
      },
      reject: () => {
        this.msgs = [{ severity: 'info', summary: 'Rejeté', detail: "La playlist n'a pas été mise à jour." }];
      }
    });
  }

}