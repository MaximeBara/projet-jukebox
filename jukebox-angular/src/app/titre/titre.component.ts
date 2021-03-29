import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TitreService } from 'src/app/services/titre.service';
import { Titre } from '../models/titre';

@Component({
  selector: 'app-titres',
  templateUrl: './titre.component.html',
  styleUrls: ['./titre.component.css']
})
export class TitreComponent implements OnInit {

  titres: Titre[] = [];

  constructor(private titreService: TitreService, private activatedRoute: ActivatedRoute) { 
  }

  ngOnInit(): void {

    const id = this.activatedRoute.snapshot.params['id'];

    this.activatedRoute.data.subscribe(data => {
      switch (data.kind) {
        case "fromPlaylists":
          this.titreService.getAllTitresByPlaylist(id).subscribe((titres: Titre[]) => {
            this.titres = titres;
          });
          break;
        default:
          this.titreService.getAllTitres().subscribe((titres: Titre[]) => {
            this.titres = titres;
          });
      }
    });

  }

}