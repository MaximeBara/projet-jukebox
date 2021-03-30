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

  motDePasse: string = "";
  profile: Profile = {
    id: 0,
    pseudo: '',
    point: '',
    motDePasse: ''
  };

  constructor(private user: UtilisateurService, private token: TokenStorageService) {
  }

  
  ngOnInit(): void {
    this.user.getByIdProfil(this.token.getUser().id).toPromise().then(
      (profileSerch: Profile) => {
        this.profile = profileSerch;
      }
    );
  }

  updateMotDePasse() {
    
    this.profile.motDePasse = this.motDePasse
    this.user.changeByIdMDP(this.profile).subscribe();
    
  }




}
