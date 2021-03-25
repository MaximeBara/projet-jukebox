import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userConnexion: FormGroup;

  constructor(private fb: FormBuilder) {
    this.userConnexion = this.fb.group({
      identifiant: ["josse", [Validators.minLength(1), Validators.required]],
      mdp: ["root", Validators.required]
    })
  }

  ngOnInit() {
  }

  connexion = () => {
    if (this.userConnexion.valid) {
      

    } else {
      console.log("erreur");

    }

  };
}
