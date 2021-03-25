import { Component, OnInit } from '@angular/core';
import {MenuItem} from 'primeng/api';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  items: MenuItem[] = [];

  constructor() { }

  ngOnInit(): void {

    this.items = [
      {
         label:'Jukebox Favoris',
         icon:'pi pi-fw pi-star',
         items:[
            {
               label:'Ajouter',
               icon:'pi pi-fw pi-plus',
            },
            {
               icon:'pi pi-fw pi-bars',
               label:'List'
            }
         ]
      },
      {
         label:'Profile',
         icon:'pi pi-fw pi-user',
         routerLink:['/profile']     
      },
      {
         label:'about',
         icon:'pi pi-fw pi-thumbs-up',
         routerLink:['/about']
      },
      {
         label:'Contacte',
         icon:'pi pi-fw pi-pencil',
         routerLink:['/contact']
      },
      {
         label:'Déconnexion',
         icon:'pi pi-fw pi-power-off',
         routerLink:['/connexion']
      }
    ];

  }

}
