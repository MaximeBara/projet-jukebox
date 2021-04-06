import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
   selector: 'app-menu-page',
   templateUrl: './menu-page.component.html',
   styleUrls: ['./menu-page.component.css']
})
export class MenuPageComponent implements OnInit {

   items: MenuItem[] = [];
   isLoggedIn = false;

   loginItem: MenuItem = {
      label: '',
      icon: '',
      routerLink: ['']
   }

   signupItem: MenuItem = {
      label: '',
      icon: '',
      routerLink: ['']
   }

   userItem: MenuItem = {
      label: '',
      icon: '',
      routerLink: ['']
   }

   constructor(private tokenStorageService: TokenStorageService) { }

   ngOnInit(): void {

      this.isLoggedIn = !!this.tokenStorageService.getToken();

      if (this.isLoggedIn) {
         const user = this.tokenStorageService.getUser();
         this.userItem = {
            label: user.pseudo,
            icon: 'pi pi-user',
            routerLink: ['/profile']
         };
         this.loginItem = {
            label: 'Déconnexion',
            icon: 'pi pi-fw pi-power-off',
            style: 'red'
         };
         this.items = [
            {
               label: 'Accueil',
               icon: 'pi pi-fw pi-home',
               routerLink: ['']
            },
            {
               label: 'Jukebox Favoris',
               icon: 'pi pi-fw pi-star',
               routerLink: ['/favoris']   
            },
            {
               label: 'Jukebox',
               icon: 'pi pi-fw pi-play',
               routerLink: ['/jukeboxes']
            },
            {
               label: 'Playlists',
               icon: 'pi pi-fw pi-list',
               routerLink: ['/playlists']
            },
            {
               label: 'Titres',
               icon: 'pi pi-fw pi-play',
               routerLink: ['/titres']
            },
            {
               label: 'A propos',
               icon: 'pi pi-fw pi-thumbs-up',
               routerLink: ['/about']
            },
            {
               label: 'Contact',
               icon: 'pi pi-fw pi-pencil',
               routerLink: ['/contact']
            }
         ];
      } else {
         this.loginItem = {
            label: 'Connexion',
            icon: 'pi pi-fw pi-power-off'
         };
         this.signupItem = {
            label: 'Inscription',
            icon: 'pi pi-fw pi-save'
         };
         this.items = [
            {
               label: 'Accueil',
               icon: 'pi pi-fw pi-home',
               routerLink: ['']
            },
            {
               label: 'A propos',
               icon: 'pi pi-fw pi-thumbs-up',
               routerLink: ['/about']
            },
            {
               label: 'Contact',
               icon: 'pi pi-fw pi-pencil',
               routerLink: ['/contact']
            }
         ];

      }

   }

   logout(): void {
      this.tokenStorageService.signOut();
      window.location.replace('');
   }

   login(): void {
      if (this.isLoggedIn) {
         const user = this.tokenStorageService.getUser();
         this.tokenStorageService.signOut();
         window.location.replace('');
      } else {
         window.location.replace('/login');
      }
   }

   signup(): void {
      if (this.isLoggedIn) {
         const user = this.tokenStorageService.getUser();
         this.tokenStorageService.signOut();
         window.location.replace('');
      } else {
         window.location.replace('/inscription');
      }
   }

}
