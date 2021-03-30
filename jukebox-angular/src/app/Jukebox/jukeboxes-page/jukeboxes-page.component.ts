import { Component, OnInit } from '@angular/core';
import { Jukebox } from 'src/app/models/jukebox';
import { JukeboxService } from 'src/app/services/jukebox.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-jukeboxes-page',
  templateUrl: './jukeboxes-page.component.html',
  styleUrls: ['./jukeboxes-page.component.css']
})
export class JukeboxesPageComponent implements OnInit {

  jukeboxes: Jukebox[] = [];
  userId= 0;

  constructor(private jukeboxService: JukeboxService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.userId = this.tokenStorageService.getUser().id;
    this.jukeboxService.getAllJukeboxByAdministrateur(this.userId).subscribe((jukeboxes: Jukebox[]) => {
      this.jukeboxes = jukeboxes;
    });
    this.userId = this.tokenStorageService.getUser().id;

  }

}
