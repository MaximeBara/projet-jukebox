import { Component, OnInit, Input } from '@angular/core';
import { Profile } from '../models/user';
import { TokenStorageService } from '../services/token-storage.service';
import { UtilisateurService } from '../services/utilisateur.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  profile!: Profile;

  constructor(private user: UtilisateurService, private token: TokenStorageService) {


  }

  ngOnInit(): void {
    
    this.user.getByIdProfil(this.token.getUser().id).subscribe(
      (profile: Profile) => {
        this.profile = profile;
        console.log("L'id du profil " + this.profile.id);
        this.profile =
        {
          id: this.profile.id,
          pseudo: this.profile.pseudo,
          point: this.profile.point
        };
      }
    );
  }

}
