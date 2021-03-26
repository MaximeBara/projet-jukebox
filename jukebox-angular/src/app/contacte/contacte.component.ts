import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators  } from '@angular/forms';

@Component({
  selector: 'app-contacte',
  templateUrl: './contacte.component.html',
  styleUrls: ['./contacte.component.css']
})
export class ContacteComponent implements OnInit {

  contacter: FormGroup;

  constructor(private fb: FormBuilder) {
    this.contacter = this.fb.group({
      subject: ['']
    })
  }

  ngOnInit() {
  }

  envoie = () => {
    if (this.contacter.valid) {
      

    } else {
      console.log("erreur");

    }

  };

}
