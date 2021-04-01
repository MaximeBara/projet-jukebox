import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { ViewEncapsulation } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { ConfirmationService, PrimeNGConfig } from 'primeng/api';
import { Playlist } from '../models/playlist';
import { Titre } from '../models/titre';
import { PlaylistService } from '../services/playlist.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  encapsulation : ViewEncapsulation.None,
  providers: [ConfirmationService]
})
export class HomePageComponent implements OnInit {

  playlists: Playlist[] = [];
  titres: Titre[] = [];
  msgs: Message[] = [];
  userId= 0;
  editMode = false;

  constructor(private playlistService: PlaylistService,
    private confirmationService: ConfirmationService,
     private primengConfig: PrimeNGConfig) { }


  ngOnInit(): void {
    this.primengConfig.ripple = true;
  }

  code() {

    this.confirmationService.confirm({

      message: 'Etes-vous sûr de vouloir supprimer cette playlist ?',

      header: 'Confirmation de suppression de la playlist ',

      icon: 'pi pi-info-circle',

      accept: () => {

        

      },

      reject: () => {

      }

    });

  }

}