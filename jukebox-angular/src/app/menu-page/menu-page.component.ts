import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { LoginComponent } from '../login/login.component';
import { TokenStorageService } from '../services/token-storage.service';

@Component({
   selector: 'app-menu-page',
   templateUrl: './menu-page.component.html',
   styleUrls: ['./menu-page.component.css']
})
export class MenuPageComponent implements OnInit {

   items: MenuItem[] = [];
   isLoggedIn = false;

   loggin: MenuItem = {
   label: '',
   icon: '',
   routerLink: ['']
   }

   constructor(private tokenStorageService: TokenStorageService) { }

   ngOnInit(): void {

      this.isLoggedIn = !!this.tokenStorageService.getToken();

      if (this.isLoggedIn) {
         const user = this.tokenStorageService.getUser();
         this.loggin = {
               label: 'Déconnexion',
               icon: 'pi pi-fw pi-power-off',
               style:'red'
         };
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
            }
         ];
      } else {
         this.loggin = {
            label: 'Login',
            icon: 'pi pi-fw pi-power-off'
         };
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
            }
         ];

      }

   }

   logout(): void {
      this.tokenStorageService.signOut();
      window.location.replace('');
   }

   btnClick(): void {
      
      if(this.isLoggedIn){
         const user = this.tokenStorageService.getUser();
         this.tokenStorageService.signOut();
         window.location.replace('');
         
      }else{
         window.location.replace('/login');

      }
     
};

}
