import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ConfirmationService, Message, PrimeNGConfig } from 'primeng/api';
import { TokenStorageService } from '../services/token-storage.service';
import { PlaylistService } from 'src/app/services/playlist.service';
import { TitreService } from 'src/app/services/titre.service';
import { Playlist } from '../models/playlist';
import { Titre } from '../models/titre';

@Component({
  selector: 'app-titres',
  templateUrl: './titre.component.html',
  styleUrls: ['./titre.component.css'],
  providers: [ConfirmationService]
})
export class TitreComponent implements OnInit {

  msgs: Message[] = [];
  titres: Titre[] = [];
  playlist: Partial<Playlist> = {};
  titre : Partial<Titre> = {};
  userId: number = 0;
  playlistId: number = 0;

  constructor(private activatedRoute: ActivatedRoute, private titreService: TitreService, 
    private playlistService: PlaylistService, private confirmationService: ConfirmationService,
    private tokenStorageService: TokenStorageService, private primengConfig: PrimeNGConfig) {
      this.primengConfig.ripple = true;
      this.userId = this.tokenStorageService.getUser().id;
  }

  ngOnInit(): void {
    const data = history.state.data;
    if (data) { // Chemin avec les data (venant de /playlists)
      this.titres = data.playlist.titres;
      this.playlist = data.playlist;
    } else {
      this.playlistId = this.activatedRoute.snapshot.params.id;
      if (this.playlistId) { // Chemin sans les data (refresh de la page)
        this.playlistService.getPlaylistById(this.playlistId).subscribe((playlist: Playlist) => {
          this.titres = playlist.titres;
          this.playlist = playlist;
        });
      } else { // Chemin venant de tous les titres (/titres)
        this.titreService.getAllTitres().subscribe((titres: Titre[]) => {
          this.titres = titres;
        });
      }
    }
  }

  confirm1(titre: Titre) {
    this.confirmationService.confirm({
      message: 'Etes-vous sûr de vouloir supprimer ce titre ?',
      header: 'Confirmation de suppression du titre ' + titre.nom,
      icon: 'pi pi-info-circle',
      accept: () => {
        this.titres = this.titres.filter(item => item.id != titre.id);
        this.playlist.titres =  this.titres;
        this.playlistService.updatePlaylistById(this.playlist).subscribe(() => {
          this.msgs = [{ severity: 'info', summary: 'Confirmé', detail: "Le titre a bien été supprimé." }];
        });
      },
      reject: () => {
        this.msgs = [{ severity: 'info', summary: 'Rejeté', detail: "Le titre n'a pas été supprimé." }];
      }
    });
  }

}