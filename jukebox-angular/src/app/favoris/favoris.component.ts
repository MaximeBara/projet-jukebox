import { Component, OnInit } from '@angular/core';
import { Jukebox } from '../models/jukebox';
import { JukeboxService } from '../services/jukebox.service';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
  selector: 'app-favoris',
  templateUrl: './favoris.component.html',
  styleUrls: ['./favoris.component.css']
})
export class FavorisComponent implements OnInit {

  jukeboxes: Jukebox[] = [];
  userId= 0;

  constructor(private jukeboxService: JukeboxService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.userId = this.tokenStorageService.getUser().id;

    this.jukeboxService.getAllFavorisByMembre(this.userId).subscribe((jukeboxes: Jukebox[]) => {
      this.jukeboxes = jukeboxes;
    });

  }

}
