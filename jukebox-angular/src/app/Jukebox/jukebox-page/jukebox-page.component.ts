import { Component, OnInit } from '@angular/core';
import { Jukebox } from 'src/app/models/jukebox';
import { Titre } from 'src/app/models/titre';
import { JukeboxService } from 'src/app/services/jukebox.service';

@Component({
  selector: 'app-jukebox-page',
  templateUrl: './jukebox-page.component.html',
  styleUrls: ['./jukebox-page.component.css']
})
export class JukeboxPageComponent implements OnInit {

  id: number = 70;

  jukebox!: Jukebox;

  titres: Titre[] = [];

  cols: any[] = [];

  constructor(private jukeboxApi: JukeboxService) { }

  ngOnInit(): void {

    this.jukeboxApi.getByIdWithPlaylistAndTitre(this.id).subscribe(
      (jukebox: Jukebox) => {
        this.jukebox = jukebox;
        console.log(jukebox);
      });

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
