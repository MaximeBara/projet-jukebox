import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {

  userInscription: FormGroup;

  constructor(private fb: FormBuilder) {
    this.userInscription = this.fb.group({
      identifiant: ["josse", [Validators.minLength(1), Validators.required]],
      mdp: ["root", Validators.required]
    })
  }

  ngOnInit() {
  }

  inscription = () => {
    if (this.userInscription.valid) {
      

    } else {
      console.log("erreur");

    }

  };

}
