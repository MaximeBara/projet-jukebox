import { Component, OnInit } from '@angular/core';
import { Titre } from 'src/app/models/titre';

@Component({
  selector: 'app-jukebox-page',
  templateUrl: './jukebox-page.component.html',
  styleUrls: ['./jukebox-page.component.css']
})
export class JukeboxPageComponent implements OnInit {

  titres: Titre[] = [];

  cols: any[] = [];

  constructor() { }

  ngOnInit(): void {

        this.titres = [
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
          {id: 1, artiste: "Dimension", lien: "d2u3BRGd2rs", nom: "Offender"},
        ]

        this.cols = [
            { field: 'id', header: 'ID' },
            { field: 'artiste', header: 'Artiste' },
            { field: 'lien', header: 'Lien' },
            { field: 'nom', header: 'Nom' }
        ];

  }

}
