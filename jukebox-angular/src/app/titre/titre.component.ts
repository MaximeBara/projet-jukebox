import { Component, OnInit } from '@angular/core';
import { TitreService } from 'src/app/services/titre.service';
import { Titre } from '../models/titre';

@Component({
  selector: 'app-titres',
  templateUrl: './titre.component.html',
  styleUrls: ['./titre.component.css']
})
export class TitreComponent implements OnInit {

  titres: Titre[] = [];

  constructor(private titreService: TitreService) { }

  ngOnInit(): void {

    this.titreService.getAllTitres().subscribe((titres: Titre[]) => {
      this.titres = titres;
      console.log(titres);
    });

  }

}
