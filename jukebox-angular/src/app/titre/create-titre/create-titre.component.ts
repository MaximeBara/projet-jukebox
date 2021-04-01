import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Titre } from '../../models/titre';
import { TitreService } from 'src/app/services/titre.service';

@Component({
  selector: 'app-create-titre',
  templateUrl: './create-titre.component.html',
  styleUrls: ['./create-titre.component.css']
})
export class CreateTitreComponent implements OnInit {

  form: any = {
    lien: ''
  }
  isSuccessful = false;
  isCreateFailed = false;
  errorMessage = '';
  attente = false;

  constructor(private titreService: TitreService, private router: Router, 
    private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.titreService.importFromYoutube(this.form.lien).subscribe(
      data => {
        // TODO : update de la playlist
        this.isSuccessful = true;
        this.isCreateFailed = false;
        setTimeout(() => {
          this.router.navigate(['/playlists/']);
        }, 1000);
        this.attente = false;
      },
      err => {
        this.isCreateFailed = true;
        this.attente = false;
      }
    );
  }

}
