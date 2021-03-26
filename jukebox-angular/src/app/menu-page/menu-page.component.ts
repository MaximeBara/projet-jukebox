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
   username?: string;

   constructor(private tokenStorageService: TokenStorageService) { }

   ngOnInit(): void {

      this.isLoggedIn = !!this.tokenStorageService.getToken();

      if (this.isLoggedIn) {
         const user = this.tokenStorageService.getUser();
         this.items = [
            {
               label: 'Jukebox Favoris',
               icon: 'pi pi-fw pi-star',
               items: [
                  {
                     label: 'Ajouter',
                     icon: 'pi pi-fw pi-plus',
                  },
                  {
                     icon: 'pi pi-fw pi-bars',
                     label: 'List'
                  }
               ]
            },
            {
               label: 'About',
               icon: 'pi pi-fw pi-thumbs-up',
               routerLink: ['/about']
            },
            {
               label: 'Contact',
               icon: 'pi pi-fw pi-pencil',
               routerLink: ['/contact']
            },
            {
               label: user.pseudo,
               routerLink: ['/profile']
            },
            {
               label: 'Déconnexion',
               icon: 'pi pi-fw pi-power-off',
               command: () => this.logout()
            }
         ];
      } else {
         this.items = [
            {
               label: 'About',
               icon: 'pi pi-fw pi-thumbs-up',
               routerLink: ['/about']
            },
            {
               label: 'Contact',
               icon: 'pi pi-fw pi-pencil',
               routerLink: ['/contact']
            },
            {
               label: 'Login',
               icon: 'pi pi-fw pi-power-off',
               routerLink: ['/login']
            }
         ];
      }

   }

   logout(): void {
      this.tokenStorageService.signOut();
      window.location.reload();
   }

}
