import { Message } from '@angular/compiler/src/i18n/i18n_ast';
import { ViewEncapsulation } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { ConfirmationService, PrimeNGConfig } from 'primeng/api';
import { Playlist } from '../models/playlist';
import { Titre } from '../models/titre';
import { PlaylistService } from '../services/playlist.service';

import { Jukebox } from 'src/app/models/jukebox';
import { JukeboxService } from 'src/app/services/jukebox.service';
import { Router } from '@angular/router';

import { TitreService } from '../services/titre.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css'],
  encapsulation : ViewEncapsulation.None,
  providers: [ConfirmationService]
})
export class HomePageComponent implements OnInit {
  [x: string]: any;

  numero: string = "";
  jukebox: Jukebox[] = [];
  playlists: Playlist[] = [];
  titres: Titre[] = [];
  msgs: Message[] = [];
  userId= 0;
  editMode = false;
 
  constructor(private playlistService: PlaylistService,
    private titreService: TitreService,
    private confirmationService: ConfirmationService,
     private primengConfig: PrimeNGConfig,
     private jukeboxService: JukeboxService,
     private router: Router) { }



  ngOnInit(): void {
    this.primengConfig.ripple = true;
    this.playlistService.getTop5Playlists().subscribe(data => {
      this.playlists = data;
    });
    this.titreService.getTop5Titres().subscribe(data => {
      this.titres = data;
    })
  }

  code() {

    this.confirmationService.confirm({

      header: 'Notez le code du jukebox ',
      
      
      accept: () => {

        console.log("sa passe")
        this.jukeboxService.getByCode(this.numero).subscribe(
          (jukeboxeSerch:Jukebox) => {
          

           jukeboxeSerch.id;

           this.router.navigate(['/jukebox/'+jukeboxeSerch.id]);
           
        });

        

      }

    });

  }

}