import { Component, OnInit } from '@angular/core';
import { PlaylistService } from 'src/app/services/playlist.service';
import { Playlist } from '../models/playlist';
import { TitreService } from 'src/app/services/titre.service';
import { Titre } from '../models/titre';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {

  playlists: Playlist[] = [];
  titres: Titre[] = [];

  constructor(private playlistService: PlaylistService) { }

  ngOnInit(): void {
    this.playlistService.getAllPlaylists().subscribe((playlists: Playlist[]) => {
      this.playlists = playlists;
      console.log(playlists);
    });
  }

}