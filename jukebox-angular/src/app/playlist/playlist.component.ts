import { Component, OnInit } from '@angular/core';
import { PlaylistService } from 'src/app/services/playlist.service';
import { Playlist } from '../models/playlist';

@Component({
  selector: 'app-playlist',
  templateUrl: './playlist.component.html',
  styleUrls: ['./playlist.component.css']
})
export class PlaylistComponent implements OnInit {

  playlists: Playlist[] = [];

  constructor(private playlistService: PlaylistService) { }

  ngOnInit(): void {
    this.playlistService.getAllPlaylists().subscribe((playlists: Playlist[]) => {
      this.playlists = playlists;
      console.log(playlists);
    });
  }

}
